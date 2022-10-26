package com.ibetar.capsulachallenge.service.impl;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.exception.BankAccountBadRequestAccountsException;
import com.ibetar.capsulachallenge.exception.BankAccountInsufficientFondsException;
import com.ibetar.capsulachallenge.exception.BankAccountNotFoundException;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankOperation;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.persistence.entity.mapper.BankAccountDtoToBankAccount;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BaseRepository;
import com.ibetar.capsulachallenge.service.BankTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BankTransactionServiceImpl extends BaseServiceImpl<BankAccount, Long> implements BankTransactionService {

    private final HashMap<Long, BankAccountDTO> bankAccountDTOHashMap= new HashMap<>();

    @Autowired
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountDtoToBankAccount mapper;

    public BankTransactionServiceImpl(BaseRepository<BankAccount, Long> baseRepository, BankAccountRepository bankAccountRepository, BankAccountDtoToBankAccount mapper) {
        super(baseRepository);
        this.bankAccountRepository = bankAccountRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public BankAccount createNewBankAccount(BankAccountDTO bankAccountDTO) {
        log.info("Creating a new bank account... ->");
        BankAccount bankAccount = mapper.map(bankAccountDTO);
        Optional<BankAccount> newBankAccount = Optional.of(bankAccountRepository.save(bankAccount));
        log.info("Account created: {} ", newBankAccount);
        return newBankAccount.orElseThrow(
                ()-> new BankAccountBadRequestAccountsException("An error has occurred while " +
                        "creating this new account. Please check your request and try again")
        );
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException {
        log.info("Fetching account number: {}", numberAccount);
        Optional<BankAccount> bankAccount = Optional.ofNullable(bankAccountRepository.findByNumberAccount(numberAccount));
        Optional<Double> balance = Optional.of(BankOperation.checkBalance(bankAccount.get()));
        log.info("Account number: {} ", numberAccount);
        return bankAccount.orElseThrow(
                ()->new BankAccountNotFoundException("Bank Account does not exist in Database. " +
                        "Please check your request"));
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public BankAccount creditBalanceByNumberAccount(String numberAccount, Double amountTransaction) throws IOException {
        log.info("Accrediting amount {} to account number: {}",amountTransaction, numberAccount);
        BankAccount account = bankAccountRepository.findByNumberAccount(numberAccount);
        double actualBalance = BankOperation.checkBalance(account);
        try {
            if ((amountTransaction > 0 && actualBalance >= 0)&& !amountTransaction.isNaN()) {
                AtomicDouble newBalance = new AtomicDouble(BankOperation.creditAmount(account,amountTransaction));
                account.setBalance(newBalance);
                bankAccountRepository.save(account);
                return account;
            }
        } catch (Exception e) {
            throw new BankAccountNotFoundException("Bank Account does not exist in Database. Please check your request");
        }
        log.error("Invalid Amount transaction");
        return account;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public BankAccount debitBalanceByNumberAccount(String numberAccount, Double amountTransaction) throws IOException {
        log.info("Consulting balance of account number {} : Debit amount transaction {}", numberAccount, amountTransaction);
        BankAccount account = bankAccountRepository.findByNumberAccount(numberAccount);
        double actualBalance = BankOperation.checkBalance(account);
        try {
            if (actualBalance >= amountTransaction) {
                log.info("Transaction: Debiting amount {} from account number {}:", amountTransaction, numberAccount);
                AtomicDouble newBalance = new AtomicDouble(BankOperation.debitAmount(account,amountTransaction));
                account.setBalance(newBalance);
                bankAccountRepository.save(account);
                log.info("Transaction request successful");
            } else {
                log.error("Account fonds are insufficient");
                log.error("Transaction request is being canceled...");
                throw new BankAccountInsufficientFondsException("Balance Account are lower than this amount transaction");
            }
            return account;
        } catch (Exception e) {
            log.error("-> Error caught with catch clause");
            throw new BankAccountInsufficientFondsException("Balance Account are lower than this amount transaction");
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public void transfer(String bankAccountNumberFrom, String bankAccountNumberTo, double amountTransaction) {
        try {
            log.info("Consulting data of accounts numbers {} and {}", bankAccountNumberFrom, bankAccountNumberTo);
            //TODO: Check if accounts exist in DB
            BankAccount bankAccountFrom = bankAccountRepository.findByNumberAccount(bankAccountNumberFrom);
            BankAccount bankAccountTo = bankAccountRepository.findByNumberAccount(bankAccountNumberTo);

            //TODO: Check both accounts balances and validate if transaction can proceed
            log.info("Checking accounts balances");
            double actualBalanceFrom = BankOperation.checkBalance(bankAccountFrom);
            double actualBalanceTo = BankOperation.checkBalance(bankAccountTo);
            log.info("AccountFrom balance = $ {} : AccountTo balance = $ {}",actualBalanceFrom,actualBalanceTo);

            //Proceed with bank operations
            log.info("Debiting amount = $ {} from account {}", amountTransaction, bankAccountFrom);
            AtomicDouble newBalanceFrom = new AtomicDouble(BankOperation.debitAmount(bankAccountFrom,amountTransaction));

            log.info("Accrediting amount = $ {} To account {}", amountTransaction, bankAccountTo);
            AtomicDouble newBalanceTo = new AtomicDouble(BankOperation.creditAmount(bankAccountTo,amountTransaction));

            //Setting and updating accounts balances
            log.info("Updating account's balances");
            bankAccountFrom.setBalance(newBalanceFrom);
            bankAccountTo.setBalance(newBalanceTo);
            log.info("AccountFrom updated balance = $ {} : AccountTo updated balance = $ {}",newBalanceFrom,newBalanceTo);

            //Updating DB
            log.info("Updating Database's registry of accounts...");
            bankAccountRepository.saveAll(List.of(bankAccountFrom,bankAccountTo));
            log.info("Transfer transaction request successful");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.NESTED)
    public Collection<BankAccount> list(int limit) {
        log.info("Finding listed pageable info...");
        return bankAccountRepository.findAll(PageRequest.of(0,limit)).toList();
    }
}
