package com.ibetar.capsulachallenge.persistence.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.service.impl.BankTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankOperationTest {

    private BankTransactionServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("When create a new account its balance should be zero")
    void checkBalanceInNewAccountShouldBeZero() {
        //given

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble());
        bankAccount.setBankUsername("ibr");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("c1");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        //then
        assertEquals(0.0, BankOperation.checkBalance(bankAccount));
        assertEquals(10.0, BankOperation.creditAmount(bankAccount, 10));

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setBalance(new AtomicDouble(10.0d));
        bankAccount2.setBankUsername("janedoe");
        bankAccount2.setId(123L);
        bankAccount2.setNumberAccount("42");
        bankAccount2.setType(AccountType.CURRENT_ACCOUNT);

        assertEquals(10.0d, BankOperation.checkBalance(bankAccount2));
    }

    /**
     * Method under test: {@link BankOperation#checkBalance(BankAccount)}
     */
    @Test
    void testCheckBalance() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(10.0d, BankOperation.checkBalance(bankAccount));
    }

    /**
     * Method under test: {@link BankOperation#checkBalance(BankAccount)}
     */
    @Test
    void testCheckBalance2() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(Double.NaN));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(Double.NaN, BankOperation.checkBalance(bankAccount));
    }

    /**
     * Method under test: {@link BankOperation#checkBalance(BankAccount)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCheckBalance3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.google.common.util.concurrent.AtomicDouble.get()" because the return value of "com.ibetar.capsulachallenge.persistence.entity.BankAccount.getBalance()" is null
        //       at com.ibetar.capsulachallenge.persistence.entity.BankOperation.checkBalance(BankOperation.java:12)
        //   See https://diff.blue/R013 to resolve this issue.

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(null);
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankOperation.checkBalance(bankAccount);
    }

    /**
     * Method under test: {@link BankOperation#checkBalance(BankAccount)}
     */
    @Test
    void testCheckBalance4() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertThrows(RuntimeException.class, () -> BankOperation.checkBalance(bankAccount));
    }

    /**
     * Method under test: {@link BankOperation#creditAmount(BankAccount, double)}
     */
    @Test
    void testCreditAmount() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(20.0d, BankOperation.creditAmount(bankAccount, 10.0d));
    }

    /**
     * Method under test: {@link BankOperation#creditAmount(BankAccount, double)}
     */
    @Test
    void testCreditAmount2() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(Double.NaN));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(Double.NaN, BankOperation.creditAmount(bankAccount, 10.0d));
    }

    /**
     * Method under test: {@link BankOperation#creditAmount(BankAccount, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreditAmount3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.google.common.util.concurrent.AtomicDouble.get()" because the return value of "com.ibetar.capsulachallenge.persistence.entity.BankAccount.getBalance()" is null
        //       at com.ibetar.capsulachallenge.persistence.entity.BankOperation.creditAmount(BankOperation.java:21)
        //   See https://diff.blue/R013 to resolve this issue.

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(null);
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankOperation.creditAmount(bankAccount, 10.0d);
    }

    /**
     * Method under test: {@link BankOperation#creditAmount(BankAccount, double)}
     */
    @Test
    void testCreditAmount4() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertThrows(RuntimeException.class, () -> BankOperation.creditAmount(bankAccount, 0.0d));
    }

    /**
     * Method under test: {@link BankOperation#debitAmount(BankAccount, double)}
     */
    @Test
    void testDebitAmount() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(0.0d, BankOperation.debitAmount(bankAccount, 10.0d));
    }

    /**
     * Method under test: {@link BankOperation#debitAmount(BankAccount, double)}
     */
    @Test
    void testDebitAmount2() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(0.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(-10.0d, BankOperation.debitAmount(bankAccount, 10.0d));
    }

    /**
     * Method under test: {@link BankOperation#debitAmount(BankAccount, double)}
     */
    @Test
    void testDebitAmount3() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(Double.NaN));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertEquals(Double.NaN, BankOperation.debitAmount(bankAccount, 10.0d));
    }

    /**
     * Method under test: {@link BankOperation#debitAmount(BankAccount, double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDebitAmount4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.google.common.util.concurrent.AtomicDouble.get()" because the return value of "com.ibetar.capsulachallenge.persistence.entity.BankAccount.getBalance()" is null
        //       at com.ibetar.capsulachallenge.persistence.entity.BankOperation.debitAmount(BankOperation.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(null);
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankOperation.debitAmount(bankAccount, 10.0d);
    }

    /**
     * Method under test: {@link BankOperation#debitAmount(BankAccount, double)}
     */
    @Test
    void testDebitAmount5() {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        assertThrows(RuntimeException.class, () -> BankOperation.debitAmount(bankAccount, 0.0d));
    }


}

