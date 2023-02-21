//package com.ibetar.capsulachallenge.persistence.entity.mapper;
//
//import com.ibetar.capsulachallenge.persistence.entity.AccountType;
//import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
//import com.ibetar.capsulachallenge.persistence.entity.BankUser;
//import com.ibetar.capsulachallenge.persistence.entity.CurrentAccount;
//import com.ibetar.capsulachallenge.persistence.entity.dto.CurrentAccountDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CurrentAccountDtoToCurrentAccount implements IMapper<CurrentAccountDto, CurrentAccount>{
//    @Override
//    public CurrentAccount map(CurrentAccountDto in) {
//        CurrentAccount currentAccount = new CurrentAccount();
//        currentAccount.setBankUsername(in.getBankUsername());
//        currentAccount.setNumberAccount(in.getNumberAccount());
//        currentAccount.setType(AccountType.CURRENT_ACCOUNT);
//        //currentAccount.setBankUser(new BankUser());
//        return currentAccount;
//    }
//}
