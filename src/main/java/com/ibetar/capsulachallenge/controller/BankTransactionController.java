package com.ibetar.capsulachallenge.controller;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.service.impl.BankTransactionServiceImpl;
import io.swagger.annotations.Api;
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

@Slf4j
@RestController
@Api
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/accounts/transactions")
@AllArgsConstructor
public class BankTransactionController extends BaseControllerImpl<BankAccount, BankTransactionServiceImpl> {

    @Autowired
    private final BankTransactionServiceImpl bankTransactionService;

    @PatchMapping("/transfer/{numberAccountFrom}/{numberAccountTo}/{amountTransaction}")
    public ResponseEntity<Response> transfer(
            @PathVariable("numberAccountFrom") String numberAccountFrom,
            @PathVariable("numberAccountTo") String numberAccountTo,
            @PathVariable Double amountTransaction) throws IOException{

        try {
            log.info("Checking and validating accounts balances...");
            if ((!Objects.equals(numberAccountFrom, numberAccountTo)) &&
                    (amountTransaction > 0 && !amountTransaction.isNaN())) {
                BankAccount bankAccountFrom = bankTransactionService.getBalanceByNumberAccount(numberAccountFrom);
                BankAccount bankAccountTo = bankTransactionService.getBalanceByNumberAccount(numberAccountTo);
                //Get accounts balances and validate them
                double actualBalanceAccountFrom = bankAccountFrom.getBalance().get();
                double actualBalanceAccountTo = bankAccountTo.getBalance().get();
                log.info("Getting balances OK ...");
                //Check out if accountFrom  "actualBalanceAccountFrom" has fonds sufficient >= amountTransaction
                log.info("Verifying transaction availability");
                if (actualBalanceAccountFrom >= amountTransaction) {
                    //If OK then Execute transaction
                    log.info("Amount transaction is enable to accredit from account {} to account {}",numberAccountFrom,numberAccountTo);
                    bankTransactionService.transfer(numberAccountFrom,numberAccountTo, amountTransaction);
                    //Update accounts balances
                    log.info("Transfer have been executed successfully");
                    return ResponseEntity.ok(Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .statusCode(HttpStatus.ACCEPTED.value())
                            .httpStatus(HttpStatus.ACCEPTED)
                            .message("Transfer have been executed successfully")
                            .build());
                }
                log.error("Fonds are insufficient. Transaction is being canceled");
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.CONFLICT.value())
                                .httpStatus(HttpStatus.CONFLICT)
                                .reason("Fonds are insufficient. Actual balance is: " + actualBalanceAccountFrom)
                                .message("Fonds of this account -> " + numberAccountFrom +
                                        " are insufficient, this account cannot transfer this amount."
                                        + " Transaction is being canceled...")
                                .build()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e + "Error creating operation");
        }
        log.error("Error creating operation. Accounts Cannot transfer to/from themself.");
        //TODO: Create exception for amount transaction NotANumber
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.CONFLICT.value())
                        .httpStatus(HttpStatus.CONFLICT)
                        .reason("Cannot transfer to itself")
                        .message("Accounts Cannot transfer to/from themself. " + numberAccountFrom + " and " +
                                numberAccountTo + "are the same" )
                        .build()
        );
    }
}
