package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;

import java.io.IOException;

public interface BankUserService extends BaseService<BankUser, Long> {
    BankUser createNewBankUser(BankUserDto bankUserDto)throws IOException;
    public BankUser addNewBankUser(BankUserDto bankUserDto);
}
