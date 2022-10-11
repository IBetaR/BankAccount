package com.ibetar.capsulachallenge.persistence.repository;

import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.util.BankAccountDataTestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository repositoryUnderTest;

    @BeforeEach
    public void setup() {
    }

    @AfterEach
    void tearDown() {
        repositoryUnderTest.deleteAll();
    }

    @Test
    @DisplayName("it Should find An Account which matches by numberAccount")
    void findAccountByNumberAccount() {

        //given
//        AccountType accountType = AccountType.CURRENT_ACCOUNT;
//        BankAccount currentAccount = new BankAccount(
//                "C1", 0,"ibr", accountType);
//        repositoryUnderTest.save(currentAccount);

        //when
        Optional<BankAccount> bankAccount = repositoryUnderTest.findById(1L);
        BankAccount bankAccount2 = repositoryUnderTest.findByNumberAccount("C1");

        //then
        assertThat(bankAccount.get().getId()).isEqualTo(1L);
        assertAll(
                ()->assertEquals(0,bankAccount2.getBalance()),
                ()->assertEquals("ibr",bankAccount2.getBankUsername()),
               // ()->assertEquals(accountType,bankAccount2.getType()),
                ()->assertEquals(1L,bankAccount2.getId())
        );
    }

    @Test
    @DisplayName("Negative testing, should return null")
    void tryToFindAccountByUnExistingNumberAccount() {

        //given
        String numberAccount = "A2";
        //when
        BankAccount bankAccount = repositoryUnderTest.findByNumberAccount(numberAccount);
        //then
        assertThat(bankAccount).isNull();

    }
}