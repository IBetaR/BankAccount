package com.ibetar.capsulachallenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ibetar.capsulachallenge.exception.BankAccountBadRequestAccountsException;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.IdentityNumberType;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankUserDtoToBankUser;
import com.ibetar.capsulachallenge.persistence.repository.BankUserRepository;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BankUserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BankUserServiceImplTest {
    @MockBean
    private BankUserDtoToBankUser bankUserDtoToBankUser;

    @MockBean
    private BankUserRepository bankUserRepository;

    @Autowired
    private BankUserServiceImpl bankUserServiceImpl;

    /**
     * Method under test: {@link BankUserServiceImpl#createNewBankUser(BankUserDto)}
     */
    @Test
    void testCreateNewBankUser() throws IOException {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        when(bankUserRepository.save((BankUser) any())).thenReturn(bankUser);

        BankUser bankUser1 = new BankUser();
        bankUser1.setEmail("jane.doe@example.org");
        bankUser1.setFirstName("Jane");
        bankUser1.setId(123L);
        bankUser1.setIdentityNumber("42");
        bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser1.setLastName("Doe");
        bankUser1.setPhoneNumber("4105551212");
        when(bankUserDtoToBankUser.map((BankUserDto) any())).thenReturn(bankUser1);

        BankUserDto bankUserDto = new BankUserDto();
        bankUserDto.setEmail("jane.doe@example.org");
        bankUserDto.setFirstName("Jane");
        bankUserDto.setId(123L);
        bankUserDto.setIdentityNumber("42");
        bankUserDto.setLastName("Doe");
        bankUserDto.setPhoneNumber("4105551212");
        assertSame(bankUser, bankUserServiceImpl.createNewBankUser(bankUserDto));
        verify(bankUserRepository).save((BankUser) any());
        verify(bankUserDtoToBankUser).map((BankUserDto) any());
    }

    /**
     * Method under test: {@link BankUserServiceImpl#createNewBankUser(BankUserDto)}
     */
    @Test
    void testCreateNewBankUser2() throws IOException {
        BankUser bankUser = new BankUser();
        bankUser.setEmail("jane.doe@example.org");
        bankUser.setFirstName("Jane");
        bankUser.setId(123L);
        bankUser.setIdentityNumber("42");
        bankUser.setIdentityNumberType(IdentityNumberType.CUIL);
        bankUser.setLastName("Doe");
        bankUser.setPhoneNumber("4105551212");
        when(bankUserRepository.save((BankUser) any())).thenReturn(bankUser);
        when(bankUserDtoToBankUser.map((BankUserDto) any()))
                .thenThrow(new BankAccountBadRequestAccountsException("An error occurred"));

        BankUserDto bankUserDto = new BankUserDto();
        bankUserDto.setEmail("jane.doe@example.org");
        bankUserDto.setFirstName("Jane");
        bankUserDto.setId(123L);
        bankUserDto.setIdentityNumber("42");
        bankUserDto.setLastName("Doe");
        bankUserDto.setPhoneNumber("4105551212");
        assertThrows(BankAccountBadRequestAccountsException.class,
                () -> bankUserServiceImpl.createNewBankUser(bankUserDto));
        verify(bankUserDtoToBankUser).map((BankUserDto) any());
    }
}

