package com.ibetar.capsulachallenge.controller.impl;

import com.ibetar.capsulachallenge.persistence.entity.Base;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.service.impl.BaseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Api
public abstract class BaseControllerImpl <E extends Base,
        S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long>{

    @Autowired
    protected S service;

    @ApiOperation(value = "Retrieve all accounts listed", notes = "This Operation returns all stored accounts.")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("bankAccounts",service.list(20)))
                        .message("Bank Accounts fetched")
                        .httpStatus(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @ApiOperation(value = "Retrieve one account by its ID", notes = "This Operation returns an account by ID")
    @GetMapping("account/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("bankAccounts",service.findById(id)))
                            .message("Bank Account fetched successfully")
                            .httpStatus(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Resources or data are not in Database.  Please try later or contact System admin")
                            .developerMessage("Error from BaseControllerImpl. Resource not found")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value()).build());
        }
    }

    @ApiOperation(value = "Create a new Bank Account",
            notes = "This operation can choose type of account. It returns a new account with 0.00 balance by default")
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("bankAccounts",service.save(entity)))
                            .message("Bank Account created successfully")
                            .httpStatus(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Bad request.  Please check your request and try again")
                            .developerMessage("Error from BaseControllerImpl. Bad request")
                            .httpStatus(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .reason("User or Bank Account number/ID is already exists in Database")
                            .build());
        }
    }

    @PatchMapping("account/balance/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error, Bad request.\"}");
        }
    }

    @ApiOperation(value = "Delete one account by its ID", notes = "This Operation delete an account by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("bankAccounts",service.delete(id)))
                            .message("Bank Account was deleted successfully")
                            .httpStatus(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Resources or data are not in Database.  Please try later or contact System admin")
                            .developerMessage("Error deleting item. Resource not found")
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .reason("User or Bank Account number/ID does not exists in Database")
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .build());
        }
    }
}
