package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface BankAccountService extends BaseService<BankAccount, String>{

//    List<BankAccount> search(String filter) throws Exception;

    BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException;

//    BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException;


}
