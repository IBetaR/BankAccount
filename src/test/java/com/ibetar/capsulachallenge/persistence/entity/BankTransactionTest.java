package com.ibetar.capsulachallenge.persistence.entity;


import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.service.impl.BankAccountServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionTest {

    private BankAccountServiceImpl bankAccountService;

    @Test
    @DisplayName(value = "Check Bank account balance")
    void checkBalance() {

        //given
        double finalBalance = 1000;

        //when
        BankTransaction bankTransaction1 = new BankTransaction(finalBalance, 1000);

        //then
        assertAll(
                ()-> assertEquals(0,BankTransaction.checkBalance(0)),
                ()-> assertEquals(bankTransaction1.finalBalance,
                        BankTransaction.checkBalance(bankTransaction1.amountTransaction))
        );
    }

    @Test
    @DisplayName(value = "Check Bank account balance")
    void createAnNewAccountAndCheckInitialBalanceIsPositiveAndZero() {

    //given
        BankAccount bankAccount = new BankAccount(
                "C1", 0,"ibr",AccountType.CURRENT_ACCOUNT);

    //then
        assertAll(
                ()-> assertEquals(0,BankTransaction.checkBalance(bankAccount.getBalance())),
                ()-> assertEquals(100,BankTransaction.creditAmount(100))
        );
    }

    @Test
    @DisplayName(value = "Credit to Bank account a random amount and check balance")
    void creditAmount() {

        //given
        double balance1 = 1000;
        double amountTransaction1 =500;
        double balance2 = 199999.5;
        double amountTransaction2 =0.5;

        //when
        double bankTransaction1 = BankTransaction.creditAmount(0.50, 1000);
        double bankTransaction2 = BankTransaction.creditAmount(0.50, 2055.5);
        double bankTransaction3 = BankTransaction.creditAmount(balance1,amountTransaction1);
        double bankTransaction4 = BankTransaction.creditAmount(balance2,amountTransaction2);

        //then
        assertAll(
                ()-> assertEquals(1000.5,bankTransaction1),
                ()-> assertEquals(2056,bankTransaction2),
                ()-> assertEquals(1500,bankTransaction3),
                ()-> assertEquals(200000,bankTransaction4)
        );
    }

    @Test
    @DisplayName(value = "Debit a random amount and check balance")
    void debitAmount() {

        //given
        double balance1 = 1000;
        double amountTransaction1 =500;
        double balance2 = 200000;
        double amountTransaction2 =0.5;

        //when
        double bankTransaction1 = BankTransaction.debitAmount(balance1,amountTransaction1);
        double bankTransaction2 = BankTransaction.debitAmount(balance2,amountTransaction2);
        double bankTransaction3 = BankTransaction.debitAmount(balance2,amountTransaction1);
        double bankTransaction4 = BankTransaction.debitAmount(bankTransaction1,amountTransaction2);

        //then
        assertAll(
                ()-> assertEquals(500,balance1,amountTransaction1),
                ()-> assertEquals(199999.5,bankTransaction2),
                ()-> assertEquals(199500,bankTransaction3),
                ()-> assertEquals(499.5,bankTransaction4)
        );
    }

    @Test
    @Disabled
    void shouldThrowExceptions(){
        BankAccount bankAccount = new BankAccount(
                "C1", 0,"ibr",AccountType.CURRENT_ACCOUNT);

//        assertAll(
//                ()->assertThrows(BankAccountInsufficientFondsException.class,BankAccount)
//        );
    }
}