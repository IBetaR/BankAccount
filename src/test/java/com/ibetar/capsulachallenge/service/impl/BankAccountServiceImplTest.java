package com.ibetar.capsulachallenge.service.impl;


import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;

import com.ibetar.capsulachallenge.util.BankAccountDataTestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @BeforeEach
    public void init() {
        BankAccountDTO mockBankAccount = BankAccountDataTestUtils.getMockBankAccount(1L);
        Mockito.doReturn(Optional.of(mockBankAccount)).when(bankAccountRepository)
                .findByNumberAccount(anyString());
    }

    @Test
    @Disabled
    void createNewBankAccount() throws Exception {
    }

    @Test
    @Disabled
    void list() {


    }

    @Test
    @Disabled
    void getBalanceByNumberAccount() throws Exception {
    }

    @Test
    @Disabled
    void creditBalanceByNumberAccount() {
    }

    @Test
    @Disabled
    void debitBalanceByNumberAccount() {
    }
}