package com.ibetar.capsulachallenge.service.impl;

import com.ibetar.capsulachallenge.exception.BankAccountBadRequestAccountsException;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankUserDtoToBankUser;
import com.ibetar.capsulachallenge.persistence.repository.BankUserRepository;
import com.ibetar.capsulachallenge.service.BankUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;
@AllArgsConstructor
@Slf4j
public class BankUserServiceImpl implements BankUserService {

    @Autowired
    private final BankUserRepository bankUserRepository;
    private final BankUserDtoToBankUser mapper;

    @Override
    public BankUser createNewBankUser(BankUserDto bankUserDto) throws IOException {
        log.info("Creating a new user... ->");
        BankUser bankUser = mapper.map(bankUserDto);
        Optional<BankUser> newBankUser = Optional.of(bankUserRepository.save(bankUser));
        log.info("Account created: {} ", newBankUser);
        return newBankUser.orElseThrow(
                ()-> new BankAccountBadRequestAccountsException("An error has occurred while " +
                        "creating this user. Please check your request and try again")
        );
    }
}
