package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.service.BankTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/accounts/transaction")
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

//    @GetMapping("/balance/{numberAccount}")
//    public double getBalance(@RequestParam("numberAccount") BankAccount bankAccount, String numberAccount){
//        return bankTransactionService.checkBalance(bankAccount, numberAccount);
//    }
}
