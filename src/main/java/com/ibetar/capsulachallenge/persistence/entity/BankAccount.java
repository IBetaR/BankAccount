package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Base{

    @Column(unique = true)
    @NotBlank
    private String numberAccount;

    @PositiveOrZero
    private double balance = 0;

    @Column(unique = true)
    @NotBlank
    private String bankUsername;

    private AccountType type;

    public BankAccount(String numberAccount, double balance, String bankUsername, AccountType type) {

        this.numberAccount = numberAccount;
        this.balance = balance;
        this.bankUsername = bankUsername;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return Double.compare(that.getBalance(), getBalance()) == 0 && getNumberAccount().equals(that.getNumberAccount()) && getBankUsername().equals(that.getBankUsername()) && getType() == that.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberAccount(), getBalance(), getBankUsername(), getType());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "numberAccount='" + numberAccount + '\'' +
                ", balance=" + balance +
                ", bankUsername='" + bankUsername + '\'' +
                ", type=" + type +
                '}';
    }
}
