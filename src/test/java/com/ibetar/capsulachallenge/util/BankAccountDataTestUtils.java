package com.ibetar.capsulachallenge.util;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;

public class BankAccountDataTestUtils {

    public static BankAccountDTO getMockBankAccount(Long accountId){
        BankAccountDTO bankAccountDTO =new BankAccountDTO();
        bankAccountDTO.setId(accountId);
        bankAccountDTO.setBankUsername("ibr");
        bankAccountDTO.setNumberAccount("C1");
        bankAccountDTO.setBalance(0.0);
        return bankAccountDTO;
    }

    public static BankAccount getMockBankAccount2 (Long accountId){
        BankAccount bankAccount =new BankAccount();
        bankAccount.setId(accountId);
        bankAccount.setBankUsername("ibr2");
        bankAccount.setNumberAccount("C2");
        bankAccount.setBalance(new AtomicDouble(0));
        return bankAccount;
    }


}
