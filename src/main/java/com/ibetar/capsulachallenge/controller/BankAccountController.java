package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.service.impl.BankAccountServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class BankAccountController extends BaseControllerImpl <BankAccount, BankAccountServiceImpl> {

    @Autowired
    private final BankAccountServiceImpl bankAccountService;

    @Autowired
    public BankAccountController(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

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
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("balance",account.getBalance()))
                            .message(Objects.equals(account.getNumberAccount(), numberAccount) ? "Account balance found successfully" : "Account Not found")
                            .httpStatus(OK)
                            .statusCode(OK.value())
                            .build()
            );
        } catch (IOException e) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Resources or data are not in Database.  Please try later or contact System admin -> " + e)
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
        BankAccount account = service.creditBalanceByNumberAccount(numberAccount,amountTransaction);
        if ((account.getNumberAccount().equals(numberAccount) && !amountTransaction.isNaN()) && (amountTransaction > 0)) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("balance",account.getBalance()))
                            .message(Objects.equals(account.getNumberAccount(),
                                    numberAccount) ? "Amount was accredited to the Account successfully" : "Account Not found")
                            .httpStatus(OK)
                            .statusCode(OK.value())
                            .build()
            );
        }
        return ResponseEntity.badRequest().body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Invalid transaction," +
                                " you cannot credit or withdraw invalid/negative amounts. check your balance if is lower than your available balance -> : "
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
           BankAccount account = service.getBalanceByNumberAccount(numberAccount);
           if((account.getNumberAccount().equals(numberAccount) && (!amountTransaction.isNaN()))
                   &&(amountTransaction>0&&(account.getBalance() >= amountTransaction))){
                service.debitBalanceByNumberAccount(numberAccount,amountTransaction);
               return ResponseEntity.ok(
                       Response.builder()
                               .timeStamp(now())
                               .data(Map.of("balance",account.getBalance()))
                               .message(Objects.equals(account.getNumberAccount(), numberAccount) ? "Account balance found successfully" : "Account Not found")
                               .httpStatus(OK)
                               .statusCode(OK.value())
                               .build()
               );
           }
        return ResponseEntity.status(CONFLICT).body(
                Response.builder()
                .timeStamp(LocalDateTime.now())
                .message("Fonds are insufficient," +
                        " you cannot debit zero/negative or withdraw an amount greater than your balance. Your available balance -> : "+ account.getBalance())
                .developerMessage("Error from client side. Bad request")
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }
}
