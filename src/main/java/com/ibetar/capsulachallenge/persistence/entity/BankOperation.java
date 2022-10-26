package com.ibetar.capsulachallenge.persistence.entity;

import org.decimal4j.util.DoubleRounder;

import java.util.stream.DoubleStream;


public record BankOperation(double amountTransaction) {

    public static double checkBalance(BankAccount account) {
        if ( !account.getNumberAccount().isBlank()) {
            return DoubleRounder.round(account.getBalance().get(),3);
        }else {
            throw new RuntimeException("This Bank account doesn't exist in database");
        }
    }

    public static double creditAmount(BankAccount account, double amountTransaction) {
        if ( amountTransaction > 0) {
            return DoubleRounder.round(
                    DoubleStream.of(account.getBalance().get(), amountTransaction).sum(), 3);
        }else {
            throw new RuntimeException("Amount transaction cannot be negative");
        }
    }

    public static double debitAmount(BankAccount account, double amountTransaction) {
        if ( amountTransaction > 0) {
        return DoubleRounder.round(
                ( account.getBalance().get() - amountTransaction), 3);
        }else {
            throw new RuntimeException("Amount transaction cannot be negative");
        }
    }

//    public static double transfer(BankAccount accountFrom, BankAccount accountTo, double amountTransaction) {
//        if ( amountTransaction > 0) {
//
//            double actualBalanceFrom = BankOperation.checkBalance(accountFrom);
//            double actualBalanceTo = BankOperation.checkBalance(accountTo);
//            return DoubleRounder.round(
//                    ( actualBalanceFrom - amountTransaction), 3);
//        }else {
//            throw new RuntimeException("Amount transaction cannot be negative");
//        }
//    }
}
