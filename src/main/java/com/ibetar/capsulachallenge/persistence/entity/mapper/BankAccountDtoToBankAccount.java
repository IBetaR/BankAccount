package com.ibetar.capsulachallenge.persistence.entity.mapper;

import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class BankAccountDtoToBankAccount implements IMapper<BankAccountDTO, BankAccount>{
    @Override
    public BankAccount map(BankAccountDTO in) {
        BankAccount bankAccount =new BankAccount();
        bankAccount.setBankUsername(in.getBankUsername());
        bankAccount.setNumberAccount(in.getNumberAccount());
        bankAccount.setBalance(0);
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        return bankAccount;
    }
}
