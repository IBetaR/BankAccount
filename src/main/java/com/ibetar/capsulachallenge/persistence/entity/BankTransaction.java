package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Data;
import org.decimal4j.util.DoubleRounder;

import java.util.stream.DoubleStream;
@Data
public class BankTransaction {

    protected static double actualBalance = 0;
    public final double finalBalance;
    public final double amountTransaction;

    public BankTransaction(double finalBalance, double amountTransaction) {
        this.finalBalance = DoubleRounder.round(finalBalance,4);
        this.amountTransaction = DoubleRounder.round(amountTransaction,4);
    }

    public static double checkBalance(double actualBalance){
        return DoubleRounder.round(actualBalance,4);
    }

    public static double creditAmount(double... finalBalance){
        return DoubleRounder.round(DoubleStream.of(finalBalance)
                .reduce(0, Double::sum),3);
    }

    public static double debitAmount(double... finalBalance){
        return DoubleRounder.round((DoubleStream.of(finalBalance)
                .reduce(0, (actualBalance,amountTransaction) -> (actualBalance - amountTransaction)*-1)*-1),4);
    }

//    public static double debitAmount(double... finalBalance){
//
//        return DoubleStream.of(finalBalance)
//                .reduce(1,
//                        (actualBalance,amountTransaction)
//                         -> (double) Math.subtractExact((int) actualBalance, (int) amountTransaction));
//    }

}
