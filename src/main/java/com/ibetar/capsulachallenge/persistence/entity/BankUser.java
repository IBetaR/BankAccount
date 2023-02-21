package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BankUser")
@Table(
        name = "bankUser",
        uniqueConstraints = {
                @UniqueConstraint(name = "bankUser_email_unique", columnNames = "email")
        }
)
@NoArgsConstructor
public final class BankUser extends Base {

    @Column(name = "firstName")
    @NotBlank
    private String firstName;

    @Column(name = "lastName")
    @NotBlank
    private String lastName;

    private IdentityNumberType identityNumberType;

    @Column(name = "userType")
    private UserType userType;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String identityNumber;

    @NotBlank
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @OneToMany(
            mappedBy = "bankUser",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<BankAccount> accounts = new ArrayList<>();

    public void addBankAccount(BankAccount bankAccount) {
        if (!this.accounts.contains(bankAccount)) {
            this.accounts.add(bankAccount);
            bankAccount.setBankUser(this);
        }
    }

    public void removeBankAccount(BankAccount bankAccount) {
        if (this.accounts.contains(bankAccount)) {
            this.accounts.remove(bankAccount);
            bankAccount.setBankUser(null);
        }
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IdentityNumberType getIdentityNumberType() {
        return identityNumberType;
    }

    public void setIdentityNumberType(IdentityNumberType identityNumberType) {
        this.identityNumberType = identityNumberType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
