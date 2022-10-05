package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Base{

    @Column(unique = true)
    private String numberAccount;

    @PositiveOrZero
    private double balance = 0;

    @Column(unique = true)
    private String bankUsername;

    private AccountType type;

    public BankAccount(String numberAccount, double balance, String bankUsername, AccountType type) {

        this.numberAccount = numberAccount;
        this.balance = balance;
        this.bankUsername = bankUsername;
        this.type = type;
    }

}
