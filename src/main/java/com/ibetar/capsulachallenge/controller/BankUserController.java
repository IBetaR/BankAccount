package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import com.ibetar.capsulachallenge.service.events.BankUserEventService;
import com.ibetar.capsulachallenge.service.impl.BankTransactionServiceImpl;
import com.ibetar.capsulachallenge.service.impl.BankUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@Api
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/users")
@AllArgsConstructor
public class BankUserController extends BaseControllerImpl<BankUser, BankUserServiceImpl> {

    @Autowired
    private final BankTransactionServiceImpl bankTransactionService;
    private final BankUserServiceImpl userService;
    private final BankUserEventService eventService;

    @ApiOperation(value = "Retrieve all users listed", notes = "This Operation returns all stored users.")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        log.info("Getting list of accounts");
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("users",service.list(20)))
                            .message("Users fetched on Bank Database")
                            .httpStatus(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Resources or data are not in Database.  Please try later or contact System admin. " + e)
                            .developerMessage("Error from BaseControllerImpl. Resource not found")
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build());
        }
    }

    @ApiOperation(value = "Create a new Bank user",
            notes = "This operation returns a new user")
    @PostMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody BankUserDto bankUserDto){

        try {
            this.eventService.publish(bankUserDto);
            log.info("eventService.toString()");
            return ResponseEntity.status(CREATED).body(
                    Response.builder()
                            .timeStamp(now())
                            .data(Map.of("users",service.createNewBankUser(bankUserDto)))
                            .message("User created successfully")
                            .httpStatus(CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Error creating new user. " + e)
                            .developerMessage("Error from BankUSerController.")
                            .reason(e.getMessage())
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Error creating new user. User is already in database " + e)
                            .developerMessage("Error from BankUSerController.")
                            .reason(e.getMessage())
                            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .build());
        }
    }
}

