package com.ibetar.capsulachallenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.IdentityNumberType;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankAccountDtoToBankAccount;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BaseRepository;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class BankTransactionServiceImplTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BankTransactionServiceImpl#BankTransactionServiceImpl(BaseRepository, BankAccountRepository, BankAccountDtoToBankAccount)}
     *   <li>{@link BankTransactionServiceImpl#transfer(String, String, double)}
     * </ul>
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BankTransactionServiceImpl.bankAccountDTOHashMap
        //     BankTransactionServiceImpl.bankAccountRepository
        //     BankTransactionServiceImpl.mapper
        //     BaseServiceImpl.baseRepository

        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .transfer("42", "42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#createNewBankAccount(BankAccountDTO)}
     */
    @Test
    void testCreateNewBankAccount() {

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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        BankTransactionServiceImpl bankTransactionServiceImpl = new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount());

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBalance(10.0d);
        bankAccountDTO.setBankUser(bankUser1);
        bankAccountDTO.setBankUsername("janedoe");
        bankAccountDTO.setId(123L);
        bankAccountDTO.setNumberAccount("42");
        assertSame(bankAccount, bankTransactionServiceImpl.createNewBankAccount(bankAccountDTO));
        verify(bankAccountRepository).save((BankAccount) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#createNewBankAccount(BankAccountDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewBankAccount2() {
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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount);
        BankTransactionServiceImpl bankTransactionServiceImpl = new BankTransactionServiceImpl(
                (BaseRepository<BankAccount, Long>) mock(BaseRepository.class), bankAccountRepository, null);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBalance(10.0d);
        bankAccountDTO.setBankUser(bankUser1);
        bankAccountDTO.setBankUsername("janedoe");
        bankAccountDTO.setId(123L);
        bankAccountDTO.setNumberAccount("42");
        bankTransactionServiceImpl.createNewBankAccount(bankAccountDTO);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#createNewBankAccount(BankAccountDTO)}
     */
    @Test
    void testCreateNewBankAccount3() {
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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountDtoToBankAccount bankAccountDtoToBankAccount = mock(BankAccountDtoToBankAccount.class);
        when(bankAccountDtoToBankAccount.map((BankAccountDTO) any())).thenReturn(bankAccount1);
        BankTransactionServiceImpl bankTransactionServiceImpl = new BankTransactionServiceImpl(
                (BaseRepository<BankAccount, Long>) mock(BaseRepository.class), bankAccountRepository,
                bankAccountDtoToBankAccount);

        BankUser bankUser2 = new BankUser();
        bankUser2.setEmail("jane.doe@example.org");
        bankUser2.setFirstName("Jane");
        bankUser2.setId(123L);
        bankUser2.setIdentityNumber("42");
        bankUser2.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser2.setLastName("Doe");
        bankUser2.setPhoneNumber("4105551212");

        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBalance(10.0d);
        bankAccountDTO.setBankUser(bankUser2);
        bankAccountDTO.setBankUsername("janedoe");
        bankAccountDTO.setId(123L);
        bankAccountDTO.setNumberAccount("42");
        assertSame(bankAccount, bankTransactionServiceImpl.createNewBankAccount(bankAccountDTO));
        verify(bankAccountRepository).save((BankAccount) any());
        verify(bankAccountDtoToBankAccount).map((BankAccountDTO) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    void testGetBalanceByNumberAccount() throws IOException {

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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertSame(bankAccount,
                (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                        .getBalanceByNumberAccount("42"));
        verify(bankAccountRepository).findByNumberAccount((String) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    void testGetBalanceByNumberAccount2() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .getBalanceByNumberAccount("42");
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBalanceByNumberAccount3() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenThrow(new BankAccountNotFoundException("An error occurred"));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .getBalanceByNumberAccount("42");
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    void testGetBalanceByNumberAccount4() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(Double.NaN));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .getBalanceByNumberAccount("42");
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBalanceByNumberAccount5() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(null);
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .getBalanceByNumberAccount("42");
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#getBalanceByNumberAccount(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetBalanceByNumberAccount6() throws IOException {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .getBalanceByNumberAccount("42");
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount() throws IOException {
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

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        BankAccount actualCreditBalanceByNumberAccountResult = (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).creditBalanceByNumberAccount("42", 10.0d);
        assertSame(bankAccount, actualCreditBalanceByNumberAccountResult);
        assertEquals(20.0d, actualCreditBalanceByNumberAccountResult.getBalance().get());
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount2() throws IOException {

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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any()))
                .thenThrow(new BankAccountNotFoundException("An error occurred"));
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountNotFoundException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).creditBalanceByNumberAccount("42", 10.0d));
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount3() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
        verify(bankAccount, atLeast(1)).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount, atLeast(1)).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreditBalanceByNumberAccount4() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenThrow(new BankAccountNotFoundException("An error occurred"));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount5() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(-0.5d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount6() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(Double.NaN));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreditBalanceByNumberAccount7() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(null);
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreditBalanceByNumberAccount8() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#creditBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testCreditBalanceByNumberAccount9() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .creditBalanceByNumberAccount("42", 0.0d);
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount() throws IOException {
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

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        BankAccount actualDebitBalanceByNumberAccountResult = (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).debitBalanceByNumberAccount("42", 10.0d);
        assertSame(bankAccount, actualDebitBalanceByNumberAccountResult);
        assertEquals(0.0d, actualDebitBalanceByNumberAccountResult.getBalance().get());
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount2() throws IOException {

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
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any()))
                .thenThrow(new BankAccountNotFoundException("An error occurred"));
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountInsufficientFondsException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).debitBalanceByNumberAccount("42", 10.0d));
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount3() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .debitBalanceByNumberAccount("42", 10.0d);
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccountRepository).save((BankAccount) any());
        verify(bankAccount, atLeast(1)).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount, atLeast(1)).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDebitBalanceByNumberAccount4() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenThrow(new BankAccountNotFoundException("An error occurred"));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .debitBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount5() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(0.5d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountInsufficientFondsException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).debitBalanceByNumberAccount("42", 10.0d));
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount6() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(Double.NaN));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountInsufficientFondsException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).debitBalanceByNumberAccount("42", 10.0d));
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDebitBalanceByNumberAccount7() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(null);
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .debitBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDebitBalanceByNumberAccount8() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .debitBalanceByNumberAccount("42", 10.0d);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#debitBalanceByNumberAccount(String, Double)}
     */
    @Test
    void testDebitBalanceByNumberAccount9() throws IOException {

        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        BankAccount bankAccount = mock(BankAccount.class);
        when(bankAccount.getBalance()).thenReturn(new AtomicDouble(10.0d));
        when(bankAccount.getNumberAccount()).thenReturn("42");
        doNothing().when(bankAccount).setBalance((AtomicDouble) any());
        doNothing().when(bankAccount).setBankUser((BankUser) any());
        doNothing().when(bankAccount).setBankUsername((String) any());
        doNothing().when(bankAccount).setNumberAccount((String) any());
        doNothing().when(bankAccount).setType((AccountType) any());
        doNothing().when(bankAccount).setId((Long) any());
        bankAccount.setBalance(new AtomicDouble(10.0d));
        bankAccount.setBankUser(bankUser);
        bankAccount.setBankUsername("janedoe");
        bankAccount.setId(123L);
        bankAccount.setNumberAccount("42");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setBalance(new AtomicDouble(10.0d));
        bankAccount1.setBankUser(bankUser1);
        bankAccount1.setBankUsername("janedoe");
        bankAccount1.setId(123L);
        bankAccount1.setNumberAccount("42");
        bankAccount1.setType(AccountType.CURRENT_ACCOUNT);
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.save((BankAccount) any())).thenReturn(bankAccount1);
        when(bankAccountRepository.findByNumberAccount((String) any())).thenReturn(bankAccount);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountInsufficientFondsException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).debitBalanceByNumberAccount("42", 0.0d));
        verify(bankAccountRepository).findByNumberAccount((String) any());
        verify(bankAccount).getBalance();
        verify(bankAccount).getNumberAccount();
        verify(bankAccount).setBalance((AtomicDouble) any());
        verify(bankAccount).setBankUser((BankUser) any());
        verify(bankAccount).setBankUsername((String) any());
        verify(bankAccount).setNumberAccount((String) any());
        verify(bankAccount).setType((AccountType) any());
        verify(bankAccount).setId((Long) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#list(int)}
     */
    @Test
    void testList() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R026 Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: Duplicate mock definition [MockDefinition@1a9e1037 name = '', typeToMock = com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository, extraInterfaces = set[[empty]], answer = RETURNS_DEFAULTS, serializable = false, reset = AFTER]
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterator.forEachRemaining(Spliterator.java:332)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        //       at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:596)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.Spliterators$ArraySpliterator.tryAdvance(Spliterators.java:1002)
        //       at java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
        //       at java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
        //   See https://diff.blue/R026 to resolve this issue.

        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertTrue(
                (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                        .list(1)
                        .isEmpty());
        verify(bankAccountRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#list(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList2() {

        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findAll((Pageable) any())).thenReturn(null);
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .list(1);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#list(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList3() {

        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        (new BankTransactionServiceImpl(baseRepository, bankAccountRepository, new BankAccountDtoToBankAccount()))
                .list(0);
    }

    /**
     * Method under test: {@link BankTransactionServiceImpl#list(int)}
     */
    @Test
    void testList4() {
        BankAccountRepository bankAccountRepository = mock(BankAccountRepository.class);
        when(bankAccountRepository.findAll((Pageable) any()))
                .thenThrow(new BankAccountNotFoundException("An error occurred"));
        BaseRepository<BankAccount, Long> baseRepository = (BaseRepository<BankAccount, Long>) mock(BaseRepository.class);
        assertThrows(BankAccountNotFoundException.class, () -> (new BankTransactionServiceImpl(baseRepository,
                bankAccountRepository, new BankAccountDtoToBankAccount())).list(1));
        verify(bankAccountRepository).findAll((Pageable) any());
    }
}

