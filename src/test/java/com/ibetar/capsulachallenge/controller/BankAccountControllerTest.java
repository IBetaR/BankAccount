package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankTransaction;

import com.ibetar.capsulachallenge.persistence.entity.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BankAccountControllerTest {

    BankAccount currentAccount = new BankAccount(
            "C1", 0,"ibr", AccountType.CURRENT_ACCOUNT);
    BankAccount savingAccount = new BankAccount(
            "C1", 0,"ibr", AccountType.SAVINGS_ACCOUNT);
    BankAccount bankAccount = new BankAccount(
            "C1", 0,"ibr", AccountType.CURRENT_ACCOUNT);

    @Test
    void createNewBankAccount() {

        assertAll(
                ()-> assertEquals(0, BankTransaction.checkBalance(bankAccount.getBalance())),
                ()-> assertEquals(100,BankTransaction.creditAmount(100)),
                ()-> assertEquals(bankAccount.getBankUsername(),"ibr")
        );
    }

    @Test
    void getBalanceByNumberAccount() {
        assertAll(
                ()->assertEquals(0,BankTransaction.checkBalance(bankAccount.getBalance()))
        );
    }

    @Test
    void creditBalanceByNumberAccount() {
        assertAll(
                ()->assertEquals(1000,BankTransaction.creditAmount(1000))
        );
    }

    @Test
    void debitBalanceByNumberAccount() {
        assertAll(
                ()->assertEquals(1000,BankTransaction.creditAmount(1000))
        );
    }

    //Negative Testing

    @Test
    @Disabled
    void getBalanceByUnExistingNumberAccount() {

    }

    @Test
    @Disabled
    void creditAInvalidAmountToBalance() {

    }

    @Test
    @Disabled
    void debitAInvalidAmountToBalance() {

    }
}