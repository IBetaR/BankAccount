package com.ibetar.capsulachallenge.service.impl;


import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;

import com.ibetar.capsulachallenge.util.BankAccountDataTestUtils;
import com.ibetar.capsulachallenge.util.ExceptionMessagesEnum;
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
                .findById(mockBankAccount.getId());
    }

    @Test
    @Disabled
    void createNewBankAccount() throws Exception {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1L);

        BankAccountNotFoundException notFoundException = Assertions.assertThrows(BankAccountNotFoundException.class,
                ()-> bankAccountService.findById(1L));

        Assertions.assertEquals(ExceptionMessagesEnum.ACCOUNT_NOT_FOUND.getValue(),notFoundException.getMessage());
        Assertions.assertEquals("ibr",bankAccount.getBankUsername());

    }

    @Test
    @Disabled
    void list() {


    }

    @Test
    @Disabled
    void getBalanceByNumberAccount() throws Exception {
        BankAccount bankAccount = BankAccountDataTestUtils.getMockBankAccount2(1L);
        Mockito.when(bankAccountService.getBalanceByNumberAccount(anyString()))
                .thenThrow();


        BankAccountNotFoundException notFoundException = Assertions.assertThrows(BankAccountNotFoundException.class,
                ()-> bankAccountService.findById(1L));

        Assertions.assertEquals(BankAccountNotFoundException.class,notFoundException.getMessage());
        //Assertions.assertEquals(0,bankAccount.getBalance());
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