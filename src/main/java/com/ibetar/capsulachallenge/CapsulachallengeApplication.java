package com.ibetar.capsulachallenge;

import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;

import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CapsulachallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapsulachallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BankAccountRepository bankAccountRepository){
		return args -> {
			BankAccount bankAccount =new BankAccount();
			bankAccount.setBankUsername("ibr");
			bankAccount.setNumberAccount("C1");
			bankAccount.setType(AccountType.CURRENT_ACCOUNT);
			bankAccountRepository.save(bankAccount);
		};
	}
}
