package com.ibetar.capsulachallenge.persistence.repository;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount, Long>{

    BankAccount findByNumberAccount(String numberAccount);
}
