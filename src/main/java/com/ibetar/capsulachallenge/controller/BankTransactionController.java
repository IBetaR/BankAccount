package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.persistence.entity.BankTransaction;
import com.ibetar.capsulachallenge.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/accounts/transaction")
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

//    @PostMapping("/balance/{id}")
//    public BankTransaction getBalance(@RequestParam("id") String accountId, @RequestBody BankTransaction checkBalance){
//        BankTransaction bankTransaction = bankTransactionService.(checkBalance);
//        return bankTransaction;
//    }
}
