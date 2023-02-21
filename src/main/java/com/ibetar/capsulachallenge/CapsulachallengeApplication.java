package com.ibetar.capsulachallenge;

import com.google.common.util.concurrent.AtomicDouble;
import com.ibetar.capsulachallenge.persistence.entity.*;

import com.ibetar.capsulachallenge.persistence.repository.BankAccountRepository;
import com.ibetar.capsulachallenge.persistence.repository.BankUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CapsulachallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapsulachallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BankAccountRepository bankAccountRepository,
							 BankUserRepository bankUserRepository
							 //KafkaTemplate<String, String> kafkaTemplate
	){
		return args -> {

			BankAccount bankAccount0 = new BankAccount();
			bankAccount0.setBalance(new AtomicDouble());
			//bankAccount0.setBankUser(new BankUser());
			bankAccount0.setBankUsername("Pbr");
			bankAccount0.setNumberAccount("C0");
			bankAccount0.setType(AccountType.OTHER);

			BankAccount bankAccount1 = new BankAccount();
			bankAccount1.setBalance(new AtomicDouble());
			//bankAccount1.setBankUser(new BankUser());
			bankAccount1.setBankUsername("Aru");
			bankAccount1.setNumberAccount("C2");
			bankAccount1.setType(AccountType.SAVINGS);

			BankAccount bankAccount2 = new BankAccount();
			bankAccount2.setBalance(new AtomicDouble());
			//bankAccount2.setBankUser(new BankUser());
			bankAccount2.setBankUsername("Ibr");
			bankAccount2.setNumberAccount("C1");
			bankAccount2.setType(AccountType.CURRENT);

			BankUser bankUser1 = new BankUser();
			bankUser1.addBankAccount(bankAccount1);
			bankUser1.setEmail("aru@example.org");
			bankUser1.setFirstName("Aru");
			bankUser1.setIdentityNumberType(IdentityNumberType.CUIL);
			bankUser1.setUserType(UserType.OTHER_PERSON);
			bankUser1.setIdentityNumber("20-95-6");
			bankUser1.setLastName("BetancourtC");
			bankUser1.setPhoneNumber("+54-12345756");

			BankUser bankUser2 = new BankUser();
			bankUser2.addBankAccount(bankAccount2);
			bankUser2.setEmail("ibr@example.org");
			bankUser2.setFirstName("Ilich");
			bankUser2.setIdentityNumberType(IdentityNumberType.CUIL);
			bankUser2.setUserType(UserType.NATURAL_PERSON);
			bankUser2.setIdentityNumber("20-95783711-6");
			bankUser2.setLastName("Betancourt Rangel");
			bankUser2.setPhoneNumber("+54-12345");

			BankUser bankUser3 = new BankUser();
			bankUser3.addBankAccount(bankAccount0);
			bankUser3.setEmail("pbr@example.org");
			bankUser3.setFirstName("Pablo");
			bankUser3.setUserType(UserType.LEGAL_PERSON);
			bankUser3.setIdentityNumberType(IdentityNumberType.CUIT);
			bankUser3.setIdentityNumber("20099153");
			bankUser3.setLastName("Betancourt Rangel");
			bankUser3.setPhoneNumber("+58-12345678");

//			List<BankUser> users = new ArrayList<>();
//			users.add(bankUser2);

			bankUserRepository.saveAll(List.of(bankUser1,bankUser2, bankUser3));
			bankAccountRepository.saveAll(List.of( bankAccount0,bankAccount1,bankAccount2));

			//kafkaTemplate.send("ibetar","hello Ibetar");

		};
	}
}
