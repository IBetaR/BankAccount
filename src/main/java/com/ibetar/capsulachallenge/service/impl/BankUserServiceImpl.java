package com.ibetar.capsulachallenge.service.impl;

import com.ibetar.capsulachallenge.exception.BankAccountBadRequestAccountsException;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankUserDtoToBankUser;
import com.ibetar.capsulachallenge.persistence.repository.BankUserRepository;
import com.ibetar.capsulachallenge.persistence.repository.BaseRepository;
import com.ibetar.capsulachallenge.service.BankUserService;
import com.ibetar.capsulachallenge.service.events.BankUserEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class BankUserServiceImpl extends BaseServiceImpl<BankUser,Long> implements BankUserService {

    @Autowired
    private final BankUserRepository bankUserRepository;
    private final BankUserDtoToBankUser mapper;
    private final BankUserEventService eventService;

    public BankUserServiceImpl(BaseRepository<BankUser, Long> baseRepository, BankUserRepository bankUserRepository, BankUserDtoToBankUser mapper, BankUserEventService eventService) {
        super(baseRepository);
        this.bankUserRepository = bankUserRepository;
        this.mapper = mapper;
        this.eventService = eventService;
    }

    @Override
    public BankUser createNewBankUser(BankUserDto bankUserDto) throws IOException {
        log.info("Creating a new user... ->");
        BankUser bankUser = mapper.map(bankUserDto);
        Optional<BankUser> newBankUser = Optional.of(bankUserRepository.save(bankUser));
        log.info("User created: {} ", newBankUser);

        //TODO: Verify that the method publish is sending the message to kafka messages bus

        this.eventService.publish(bankUserDto);
        log.info("eventService.toString()");
        return newBankUser.orElseThrow(
                ()-> new BankAccountBadRequestAccountsException("An error has occurred while " +
                        "creating this user. Please check your request and try again")
        );
    }

    @Override
    public BankUser addNewBankUser(BankUserDto bankUserDto) {
        log.info("Creating a new user... ->");
        BankUser bankUser = mapper.map(bankUserDto);
        Optional<BankUser> newBankUser = Optional.of(bankUserRepository.save(bankUser));
        log.info("User created: {} ", newBankUser);
        return newBankUser.orElseThrow(
                ()-> new BankAccountBadRequestAccountsException("An error has occurred while " +
                        "creating this user. Please check your request and try again")
        );
    }
}
