package com.ibetar.capsulachallenge.persistence.entity;

import com.google.common.util.concurrent.AtomicDouble;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity(name = "BankAccount")
@Table(name = "BankAccount")
@NoArgsConstructor
@Getter
@Setter
public class BankAccount extends Base{
    //TODO: This class must be abstract, then create a new classes that will extends of this class,
    // it could be the same Base or create a new one

    @Column(unique = true)
    @NotBlank
    private String numberAccount;

    @PositiveOrZero
    private AtomicDouble balance = new AtomicDouble(0);

    @Column(unique = true)
    @NotBlank
    private String bankUsername;

    private AccountType type;

    @ManyToOne(optional = true)
    @JoinColumn(
            name = "bankUser_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "bankUser_bankAccount_fk"
            )
    )
    private BankUser bankUser;

    public BankAccount(String numberAccount,
                       AtomicDouble balance,
                       String bankUsername,
                       AccountType type) {

        this.numberAccount = numberAccount;
        this.balance = balance;
        this.bankUsername = bankUsername;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount that)) return false;
        return getNumberAccount().equals(that.getNumberAccount()) &&
                getBalance().equals(that.getBalance()) &&
                getBankUsername().equals(that.getBankUsername()) &&
                getType() == that.getType();
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
