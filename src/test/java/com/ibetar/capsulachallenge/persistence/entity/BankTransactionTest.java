package com.ibetar.capsulachallenge.persistence.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionTest {


    @Test
    @DisplayName(value = "Check Bank account balance")
    void checkBalance() {
        double finalBalance = 1000;
        BankTransaction bankTransaction1 = new BankTransaction(finalBalance, 1000);
        assertAll(
                ()-> assertEquals(0,BankTransaction.checkBalance(0)),
                ()-> assertEquals(bankTransaction1.finalBalance,
                        BankTransaction.checkBalance(bankTransaction1.amountTransaction))

        );

    }

    @Test
    @DisplayName(value = "Check Bank account balance")
    void createAnNewAccountAndCheckInitialBalanceIsPositiveAndZero() {

        BankAccount bankAccount = new BankAccount(
                "C1", 0,"ibr",AccountType.CURRENT_ACCOUNT);

        assertAll(
                ()-> assertEquals(0,BankTransaction.checkBalance(bankAccount.getBalance())),
                ()-> assertEquals(100,BankTransaction.creditAmount(100))
        );

    }

    @Test
    @DisplayName(value = "Credit to Bank account a random amount and check balance")
    void creditAmount() {
        double balance1 = 1000;
        double amountTransaction1 =500;
        double balance2 = 199999.5;
        double amountTransaction2 =0.5;
        assertAll(
                ()-> assertEquals(1000.5,BankTransaction.creditAmount(0.50, 1000)),
                ()-> assertEquals(1500,BankTransaction.creditAmount(1500)),
                ()-> assertEquals(1500,BankTransaction.creditAmount(balance1,amountTransaction1)),
                ()-> assertEquals(200000,BankTransaction.creditAmount(balance2,amountTransaction2))

        );
    }

    @Test
    @DisplayName(value = "Debit a random amount and check balance")
    void debitAmount() {
        double balance1 = 1000;
        double amountTransaction1 =500;
        double balance2 = 200000;
        double amountTransaction2 =0.5;
        assertAll(
                ()-> assertEquals(500,balance1,amountTransaction1),
                ()-> assertEquals(500,BankTransaction.debitAmount(balance1,amountTransaction1)),
                ()-> assertEquals(199999.5,BankTransaction.debitAmount(balance2,amountTransaction2))
        );
    }
}