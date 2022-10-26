package com.ibetar.capsulachallenge.controller;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankOperation;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
//import com.ibetar.capsulachallenge.service.impl.BankAccountServiceImpl;

import com.ibetar.capsulachallenge.service.impl.BankTransactionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@Api
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/accounts")
@AllArgsConstructor
public class BankAccountController extends BaseControllerImpl <BankAccount, BankTransactionServiceImpl> {

    @Autowired
    private final BankTransactionServiceImpl bankTransactionService;

    @ApiOperation(value = "Create a new Bank Account",
            notes = "This operation returns a new account with 0.00 balance by default")
    @PostMapping("/new")
    public ResponseEntity<?> createNewBankAccount(@RequestBody BankAccountDTO bankAccountDTO){
        try {
            return ResponseEntity.status(CREATED).body(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("accounts",service.createNewBankAccount(bankAccountDTO)))
                            .message("Account created successfully")
                            .httpStatus(CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e + "Error creating new account");
        }
    }

    @ApiOperation(value = "Get balance from a Bank Account based on its number account",
            notes = "This operation returns a Bank Account based on its number account")
    @GetMapping("/account/balance/{numberAccount}")
    public ResponseEntity<Response> getBalanceByNumberAccount(@PathVariable("numberAccount") String numberAccount) throws IOException {
        try {
            BankAccount account = service.getBalanceByNumberAccount(numberAccount);
            log.info("Getting balance...");
            log.info("Your balance is: {}",account.getBalance());
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("balance",account.getBalance()))
                            .message("Account number: " + account.getNumberAccount())
                            .httpStatus(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (IOException e) {
            log.error("Resources or data are not in Database.  Please try later or contact System admin");
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Resources or data are not in Database.  Please try later or contact" +
                                    " System admin -> " + e)
                            .developerMessage("Error from BankAccount Controller. Resource not found")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value()).build());
        }
    }

    @ApiOperation(value = "This Bank transaction accredits an amount to one Bank Account based on its number account",
            notes = "This operation adds that amount and returns an updated Bank Account's balance")
    @PatchMapping("/account/balance/credit/{numberAccount}/{amountTransaction}")
    public ResponseEntity<Response> creditBalanceByNumberAccount(@PathVariable("numberAccount") String numberAccount,
                                                                 @PathVariable Double amountTransaction) throws IOException {
        log.info("Getting account data...");
        BankAccount account = service.creditBalanceByNumberAccount(numberAccount,amountTransaction);
        if ((account.getNumberAccount().equals(numberAccount)) && (!amountTransaction.isNaN()) && (amountTransaction > 0)) {
            log.info("Accrediting to account..." + amountTransaction);
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("balance",account.getBalance()))
                            .message(Objects.equals(account.getNumberAccount(),
                                    numberAccount) ? "Amount was accredited to the Account successfully"
                                    : "Account Not found")
                            .httpStatus(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
        log.error("Invalid transaction. you cannot credit or withdraw invalid/negative amounts");
        return ResponseEntity.badRequest().body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Invalid transaction," +
                                " you cannot credit or withdraw invalid/negative amounts. check your balance" +
                                " if is lower than your available balance -> : "
                                + account.getBalance() + " . Or check your transactions requests")
                        .developerMessage("Error from Client side. Bad request")
                        .httpStatus(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build()
        );
    }

    @ApiOperation(value = "This Bank transaction debits an amount to one Bank Account based on its number account",
            notes = "This operation subtract that amount and returns an updated Bank Account's balance")
    @PatchMapping("/account/balance/debit/{numberAccount}/{amountTransaction}")
    public ResponseEntity<Response> debitBalanceByNumberAccount(@PathVariable("numberAccount") String numberAccount,
                                                                 @PathVariable Double amountTransaction) throws IOException {
           log.info("Getting account data...");
           BankAccount account = service.getBalanceByNumberAccount(numberAccount);
           if((account.getNumberAccount().equals(numberAccount) && (!amountTransaction.isNaN()))
                   &&(amountTransaction> 0 && (account.getBalance().get() >= amountTransaction))){
                service.debitBalanceByNumberAccount(numberAccount,amountTransaction);
               log.info("Debiting from account $ {}...", amountTransaction);
               return ResponseEntity.ok(
                       Response.builder()
                               .timeStamp(now())
                               .data(Map.of("balance",account.getBalance()))
                               .message(Objects.equals(account.getNumberAccount(), numberAccount) ?
                                       "Account balance found successfully" : "Account Not found")
                               .httpStatus(OK)
                               .statusCode(OK.value())
                               .build()
               );
           }
        log.error("Invalid transaction. you cannot credit or withdraw invalid/negative amounts");
        return ResponseEntity.status(CONFLICT).body(
                Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Fonds are insufficient," +
                        " you cannot debit zero/negative or withdraw an amount greater than your balance." +
                        " Your available balance -> : "+ account.getBalance())
                .developerMessage("Error from client side. Bad request")
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }
}
