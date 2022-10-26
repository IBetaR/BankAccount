package com.ibetar.capsulachallenge.persistence.repository;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankAccountRepository extends BaseRepository<BankAccount, Long>{

    @Transactional
    BankAccount findByNumberAccount(String numberAccount);
}
