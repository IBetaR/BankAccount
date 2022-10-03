package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankTransaction;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
//import com.ibetar.capsulachallenge.persistence.repository.BankTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankTransactionService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

//    @Autowired
//    private BankTransactionRepository bankTransactionRepository;

//    public BankTransaction getBalance(BankTransaction checkBalance){
//        return new BankTransaction(
//                getBalance(checkBalance).finalBalance, getBalance(checkBalance).getAmountTransaction()
//        );
//
//    }


}
