package com.ibetar.capsulachallenge.util;

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


}
