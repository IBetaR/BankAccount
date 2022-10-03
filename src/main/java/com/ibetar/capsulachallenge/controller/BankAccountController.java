package com.ibetar.capsulachallenge.controller;

import com.ibetar.capsulachallenge.controller.impl.BaseControllerImpl;
import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.service.impl.BankAccountServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/accounts")
public class BankAccountController extends BaseControllerImpl <BankAccount, BankAccountServiceImpl> {

    private final BankAccountServiceImpl bankAccountService;

    @Autowired
    public BankAccountController(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/account/balance/{numberAccount}")
    public ResponseEntity<Response> getBalanceByNumberAccount(@PathVariable("numberAccount") String numberAccount) throws IOException {
        BankAccount account = service.getBalanceByNumberAccount(numberAccount);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("balance",account.getBalance()))
                        .message(Objects.equals(account.getNumberAccount(), numberAccount) ? "Account balance found successfully" : "Account Not found")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


//    @PutMapping("account/credit/numberAccount")
//    public ResponseEntity<?> updateBalance(@RequestParam String numberAccount){
//        try {
//            log.info("from updateBalance controller");
//            return ResponseEntity.status(HttpStatus.OK).body(service.updateBalance(numberAccount));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Error\":\""+e.getMessage()+ ", Búsqueda no encontrada.\"}");
//        }
//    }


}
