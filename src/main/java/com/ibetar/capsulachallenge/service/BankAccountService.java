package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface BankAccountService extends BaseService<BankAccount, String>{

    BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException;

    BankAccount creditBalanceByNumberAccount(String numberAccount,Double amountTransaction)throws IOException;

    BankAccount debitBalanceByNumberAccount(String numberAccount, Double amountTransaction);

    //BankAccount credit(String numberAccount, Double amount) throws IOException;


}
