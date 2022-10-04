package com.ibetar.capsulachallenge.service.impl;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.service.BankTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    private BankAccountRepository bankAccountRepository;

}
