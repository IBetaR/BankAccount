//package com.ibetar.capsulachallenge.persistence.entity.mapper;
//
//import com.ibetar.capsulachallenge.persistence.entity.AccountType;
//import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
//import com.ibetar.capsulachallenge.persistence.entity.BankUser;
//import com.ibetar.capsulachallenge.persistence.entity.SavingAccount;
//import com.ibetar.capsulachallenge.persistence.entity.dto.SavingAccountDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SavingAccountDtoToSavingAccount implements IMapper<SavingAccountDto, SavingAccount>{
//    @Override
//    public SavingAccount map(SavingAccountDto in) {
//        SavingAccount savingAccount = new SavingAccount();
//        savingAccount.setBankUsername(in.getBankUsername());
//        savingAccount.setNumberAccount(in.getNumberAccount());
//        savingAccount.setType(AccountType.SAVINGS_ACCOUNT);
//        //savingAccount.setBankUser(new BankUser());
//        return savingAccount;
//    }
//}
