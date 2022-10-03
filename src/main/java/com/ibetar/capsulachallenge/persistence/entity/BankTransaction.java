package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Data;

import java.util.stream.DoubleStream;
@Data
public class BankTransaction {

    public static double actualBalance = 0;
    public final double finalBalance;
    public final double amountTransaction;

    public BankTransaction(double finalBalance, double amountTransaction) {
        this.finalBalance = finalBalance;
        this.amountTransaction = amountTransaction;
    }


    public static double checkBalance(double actualBalance){

        return actualBalance;
    }

    public static double creditAmount(double... finalBalance){

        return DoubleStream.of(finalBalance)
                .reduce(0, (actualBalance,amountTransaction) -> actualBalance + amountTransaction);
    }

    public static double debitAmount(double... finalBalance){
        return DoubleStream.of(finalBalance)
                .reduce(0, (actualBalance,amountTransaction) -> actualBalance - amountTransaction);
    }

}
