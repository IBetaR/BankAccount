package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;

import java.io.IOException;

public interface BankUserService {
    BankUser createNewBankUser(BankUserDto bankUserDto)throws IOException;
}
