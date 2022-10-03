package com.ibetar.capsulachallenge.persistence.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTransactionTest {

    @Test
    void checkBalance() {
        assertAll(
                ()-> assertEquals(0,BankTransaction.checkBalance(0)),
                ()-> assertEquals(1500,BankTransaction.checkBalance(1500))

        );

    }

    @Test
    void creditAmount() {
        assertAll(
                ()-> assertEquals(1000.5,BankTransaction.creditAmount(0.50, 1000)),
                ()-> assertEquals(1500,BankTransaction.creditAmount(1500))

        );
    }

    @Test
    void debitAmount() {
        assertAll(
                ()-> assertEquals(0,1000,1000),
                ()-> assertEquals(0,BankTransaction.debitAmount(0))

        );
    }

}