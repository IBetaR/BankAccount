package com.ibetar.capsulachallenge.persistence.repository;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount, String>{

    BankAccount findByNumberAccount(String numberAccount);
    BankAccount getBalanceByNumberAccount(String numberAccount);
    List<BankAccount> findByBankUsername(String bankUsername);

}
