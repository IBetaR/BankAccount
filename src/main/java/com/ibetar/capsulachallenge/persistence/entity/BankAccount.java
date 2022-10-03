package com.ibetar.capsulachallenge.persistence.entity;

import com.ibetar.capsulachallenge.persistence.entity.dto.BalanceDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
public class BankAccount extends Base{

    @Column(unique = true)
    private String numberAccount;

    private double balance;

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
