package com.ibetar.capsulachallenge.service;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;

import java.io.IOException;

public interface BankAccountService extends BaseService<BankAccount, Long>{

    BankAccount createNewBankAccount(BankAccountDTO bankAccountDTO)throws IOException;
    BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException;
    BankAccount creditBalanceByNumberAccount(String numberAccount,Double amountTransaction)throws IOException;
    BankAccount debitBalanceByNumberAccount(String numberAccount, Double amountTransaction)throws IOException;
}
