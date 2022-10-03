package com.ibetar.capsulachallenge.controller.impl;

import com.ibetar.capsulachallenge.persistence.entity.Base;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

public abstract class BaseControllerImpl <E extends Base,
        S extends BaseServiceImpl<E, String>> implements BaseController<E, String>{

    @Autowired
    protected S service;

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

    @GetMapping("account/{id}")
    public ResponseEntity<?> getOne(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Please try later.\"}");
        }
    }

//    @GetMapping("account/getBalance/{id}")
//    public ResponseEntity<?> getBalance(@PathVariable String id) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\"Please try later.\"}");
//        }
//    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error Por favor revise la request.\"}");
        }
    }


    @PatchMapping("account/balance/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody E entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error Por favor revise la request.\"}");
        }
    }

    @DeleteMapping("account/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\":\"Error Por favor revise la request.\"}");
        }
    }
}
