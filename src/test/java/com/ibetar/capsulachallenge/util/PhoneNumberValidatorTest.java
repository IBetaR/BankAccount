package com.ibetar.capsulachallenge.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {PhoneNumberValidator.class})
@ExtendWith(SpringExtension.class)
class PhoneNumberValidatorTest {

    @Autowired
    private PhoneNumberValidator underTest;

    @BeforeEach
    void setUp() {
        underTest = new PhoneNumberValidator();
    }

    @Test
    @DisplayName("This validate if a phone number has 13 characters, stars with +54 and contains only numbers")
    void itShouldValidatePhoneNumber() {

        assertFalse(underTest.test("4105551212"));
        assertFalse(underTest.test("+54"));
        assertTrue(underTest.test("+544105551212"));
        assertFalse(underTest.test("+54asd123fgh"));
    }

    
    @ParameterizedTest
    @CsvSource({
            "+534105551212, false",
            "+541126925612, true",
            "+5841055512, false",
            "+5411269256122, false",
            "+54e126925612, false",
            "+e4e126925612, false",
            "1, false"
    })
    void itShouldValidateAListOfPhoneNumbers(String phoneNumber, boolean expected){
        //when
        boolean isValid = underTest.test(phoneNumber);

        //then
        assertEquals(expected,isValid);
    }
}

