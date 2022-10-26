package com.ibetar.capsulachallenge.util;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class PhoneNumberValidator implements Predicate<String> {
    @Override
    public boolean test(String phoneNumber) {
        return
                phoneNumber.startsWith("+54") &&
                phoneNumber.length() == 13
        // &&
               // phoneNumber.contains(String.valueOf("a").toUpperCase())
               // &&
        //phoneNumber.matches("^(\\+\\d{1,2})?\\d{11}$")
        ;

    }
}
