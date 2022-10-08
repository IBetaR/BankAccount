package com.ibetar.capsulachallenge.persistence.entity;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ResponseTest {

    @Test
    void testSetData() {

        // Arrange
        Response response = new Response(
                Response.builder()
                        .data(null)
                        .timeStamp(LocalDateTime.now())
                        .message("Test")
                        .developerMessage("Testing response Entity - Developer message")
                        .reason("Reason")
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
        );
        // Assert

        Assertions.assertAll(
                ()-> response.getReason().matches("Reason"),
                ()-> response.getTimeStamp().isAfter(LocalDateTime.now()),
                ()-> response.getDeveloperMessage().equals("Testing response Entity - Developer message"),
                ()-> response.getHttpStatus().equals(HttpStatus.OK)
        );
    }
}

