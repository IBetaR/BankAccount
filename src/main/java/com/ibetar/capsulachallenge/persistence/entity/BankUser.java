package com.ibetar.capsulachallenge.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Getter
@Setter
public final class BankUser extends Base {

    @Column(unique = true)
    private String firstName;

    @Column(unique = true)
    private String lastName;

//    @Column(unique = true)
//    @NotBlank
//    private String alias;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_bankAccount")
//    private BankAccount bankAccount;

    private IdentityNumberType identityNumberType;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String identityNumber;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String phoneNumber;

//    @OneToMany
//    @JoinColumn(name = "bank_account_id")
//    private BankAccount bankAccount;

}
