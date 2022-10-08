package com.ibetar.capsulachallenge.service.impl;

import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankTransaction;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankAccountDtoToBankAccount;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BaseRepository;
import com.ibetar.capsulachallenge.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, Long> implements BankAccountService {

    private final HashMap<Long, BankAccountDTO> bankAccountDTOHashMap= new HashMap<>();

    @Autowired
    private final BankAccountRepository bankAccountRepository;

    private final BankAccountDtoToBankAccount mapper;

    public BankAccountServiceImpl(BaseRepository<BankAccount,Long> baseRepository,
                                  BankAccountRepository bankAccountRepository,
                                  BankAccountDtoToBankAccount mapper){
        super(baseRepository);
        this.bankAccountRepository = bankAccountRepository;
        this.mapper = mapper;
    }

    @Override
    public BankAccount createNewBankAccount(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = mapper.map(bankAccountDTO);
        return this.bankAccountRepository.save(bankAccount);
    }

    @Override
    public Collection<BankAccount> list(int limit) {
        log.info("Fetching all Bank accounts ->");
        return bankAccountRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException {
        log.info("Fetching account number: {}", numberAccount);
        Optional<BankAccount> bankAccount =Optional.ofNullable(bankAccountRepository.findByNumberAccount(numberAccount));
        log.info("Account number: {} ", numberAccount);
        return bankAccount.orElseThrow(
                ()->new BankAccountNotFoundException("Bank Account does not exist in Database. Please check your request"));
    }

    @Override
    public BankAccount creditBalanceByNumberAccount(String numberAccount, Double amountTransaction) {
        log.info("Accrediting amount {} to account number: {}",amountTransaction, numberAccount);
        try {
            BankAccount account = bankAccountRepository.findByNumberAccount(numberAccount);
            Double balance = BankTransaction.creditAmount(account.getBalance(),amountTransaction);
            account.setBalance(balance);
            bankAccountRepository.save(account);
            return account;
        } catch (Exception e) {
            throw new BankAccountNotFoundException("Bank Account does not exist in Database. Please check your request");
        }
    }

    @Override
    public BankAccount debitBalanceByNumberAccount(String numberAccount, Double amountTransaction) {
        log.info("Consulting balance of account number {} : Debit amount transaction {}", numberAccount, amountTransaction);
        BankAccount account = bankAccountRepository.findByNumberAccount(numberAccount);
        Double actualBalance = account.getBalance();
        try {
            if (actualBalance >= amountTransaction) {
                log.info("Transaction: Debiting amount {} from account number {}:", amountTransaction, numberAccount);
                Double balance = BankTransaction.debitAmount(account.getBalance(),amountTransaction);
                account.setBalance(balance);
                bankAccountRepository.save(account);
                log.info("Transaction request successful");
            } else {
                log.info("Account fonds are insufficient");
                log.error("Transaction request is being canceled...");
                throw new BankAccountInsufficientFondsException("Balance Account are lower than this amount transaction");
            }
            return account;
        } catch (Exception e) {
            throw new BankAccountInsufficientFondsException("Balance Account are lower than this amount transaction");
        }
    }
}
