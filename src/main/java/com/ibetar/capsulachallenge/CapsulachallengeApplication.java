package com.ibetar.capsulachallenge;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.persistence.entity.*;

import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BankUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CapsulachallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapsulachallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BankAccountRepository bankAccountRepository, BankUserRepository bankUserRepository){
		return args -> {

			BankAccount bankAccount1 = new BankAccount();
			bankAccount1.setBalance(new AtomicDouble());
			//bankAccount.setBankUser(new BankUser());
			bankAccount1.setBankUsername("Aru");
			bankAccount1.setNumberAccount("C2");
			bankAccount1.setType(AccountType.SAVINGS_ACCOUNT);

			BankAccount bankAccount2 = new BankAccount();
			bankAccount2.setBalance(new AtomicDouble());
			//bankAccount.setBankUser(new BankUser());
			bankAccount2.setBankUsername("Ibr");
			bankAccount2.setNumberAccount("C1");
			bankAccount2.setType(AccountType.CURRENT_ACCOUNT);

			BankUser bankUser1 = new BankUser();
			//bankUser.setBankAccount(bankAccount);
			bankUser1.setEmail("Aru@example.org");
			bankUser1.setFirstName("Aru");
			bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
			bankUser1.setIdentityNumber("20-95-6");
			bankUser1.setLastName("BetancourtC");
			bankUser1.setPhoneNumber("+54-12345756");

			BankUser bankUser2 = new BankUser();
			//bankUser.setBankAccount(bankAccount);
			bankUser2.setEmail("ibr@example.org");
			bankUser2.setFirstName("Ilich");
			bankUser2.setIdentityNumberType(IdentityNumberType.CUIL);
			bankUser2.setIdentityNumber("20-95783711-6");
			bankUser2.setLastName("Betancourt Rangel");
			bankUser2.setPhoneNumber("+54-12345");

			bankUserRepository.saveAll(List.of(bankUser1,bankUser2));
			bankAccountRepository.saveAll(List.of(bankAccount1,bankAccount2));

		};
	}
}
