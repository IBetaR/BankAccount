package com.ibetar.capsulachallenge.service.impl;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankTransaction;
import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BaseRepository;
import com.ibetar.capsulachallenge.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, String> implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;


    private BankTransaction bankTransaction;

    public BankAccountServiceImpl(BaseRepository<BankAccount,String> baseRepository){
        super(baseRepository);
    }


//    @Override
//    public List<BankAccount> search(String filter) throws Exception {
//        try {
//            //List<BankAccount> bankAccounts = bankAccountRepository.findByBankUsername(filter);
//            List<BankAccount> bankAccounts = bankAccountRepository.findAll();
//            List<BankAccount> userAccounts = bankAccounts.stream()
//                    .filter(bankAccount -> bankAccount.getNumberAccount().equals(filter))
//                    .limit(10).collect(Collectors.toList());
//            //Optional<BankAccount> userBalance = userAccounts.g
//            return userAccounts;
//
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }


    @Override
    public BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException {
        log.info("Fetching account number: {}", numberAccount);
        BankAccount account = bankAccountRepository.findByNumberAccount(numberAccount);
        return account;
    }

//    @Override
//    public BankAccount getBalanceByNumberAccount(String numberAccount) throws IOException {
//        log.info("Fetching account number: {}", numberAccount);
//        BankAccount account = bankAccountRepository.getBalanceByNumberAccount(numberAccount);
//        return account;
//    }

    public Object updateBalance(String numberAccount) {
        return "Update Balance";
    }

    public void showBankAccountBalance(String id, BankAccount bankAccount){
        BankAccount account = getUserAccountOrThrow(id);
        Map<String,String > info = extractAccountInfo(bankAccount);
        log.info("account: "+ account);
        log.info("info: "+ info);
        System.out.println(account);
        System.out.println(info);
    }



    //2. Grab info from Account
    public static Map<String, String> extractAccountInfo(BankAccount data) {
        Map<String,String > info = new HashMap<>();
        info.put("numberAccount", data.getNumberAccount());
        info.put("balance", String.valueOf(data.getBalance()));
        return info;
    }


    //3. UserBankAccount exist in DB
    public BankAccount getUserAccountOrThrow(String userAccountId) {
        BankAccount userBankAccount = bankAccountRepository
                .findAll()
                .stream()
                .filter(user -> user.getBankUsername().equals(userAccountId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User %s not found", userAccountId)));
        return userBankAccount;
    }

    @Override
    public Collection<BankAccount> list(int limit) {
        log.info("Fetching all servers ->");
        return bankAccountRepository.findAll(PageRequest.of(0,limit)).toList();
    }


}
