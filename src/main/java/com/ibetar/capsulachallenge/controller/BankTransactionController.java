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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

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

        //Get accounts
        BankAccount bankAccountFrom = bankTransactionService.getBalanceByNumberAccount(numberAccountFrom);
        BankAccount bankAccountTo = bankTransactionService.getBalanceByNumberAccount(numberAccountTo);

        //Get accounts balances and validate them
        double actualBalanceAccountFrom = bankAccountFrom.getBalance().get();
        double actualBalanceAccountTo = bankAccountTo.getBalance().get();

        //If OK then Execute transaction
        bankTransactionService.transfer(numberAccountFrom,numberAccountTo, amountTransaction);

        //Update accounts balances

        return ResponseEntity.ok(Response.builder()
                .timeStamp(LocalDateTime.now())
                //.data(Map.of("balance",bankAccountFrom.getBalance().get()))
                .message("Transfer success")
                .build());
    }
}
