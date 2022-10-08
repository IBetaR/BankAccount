package com.ibetar.capsulachallenge.apitest;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;

import com.ibetar.capsulachallenge.persistence.entity.Response;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankAccountDTO;
import com.ibetar.capsulachallenge.util.BankAccountDataTestUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Integration Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void shouldAssertBankAccountCreatedAndMockedExist(){
        BankAccountDTO bankAccountDTO = BankAccountDataTestUtils.getMockBankAccount(1L);

        HttpHeaders httpHeader =new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BankAccountDTO> entity =new HttpEntity<>(bankAccountDTO,httpHeader);

        Assertions.assertNotNull(bankAccountDTO);
        Assertions.assertEquals("ibr",bankAccountDTO.getBankUsername());
        Assertions.assertEquals("C1",bankAccountDTO.getNumberAccount());
        Assertions.assertEquals(0.00,bankAccountDTO.getBalance());

    }


    @Test
    @DisplayName("Testing Classes. Should not have Null fields or properties. Test will fail if timeout exceeds 400 ms")
    public void BankAccountClassHasNoNullFieldsOrProperties() throws Exception{

        String urlNewAccount = "http://localhost:" + port + "/api/accounts/new";
        String urlGetAccounts = "http://localhost:" + port + "/api/accounts";

        assertThat(this.testRestTemplate.getForEntity(urlNewAccount,
                BankAccount.class)).hasNoNullFieldsOrProperties();

        assertThat(this.testRestTemplate.getForEntity(urlGetAccounts,BankAccountDTO.class)).hasNoNullFieldsOrProperties();
    }

    @Test
    @Timeout(value = 400 , unit = TimeUnit.MILLISECONDS)
    @DisplayName("Testing Classes. Should not have Null fields or properties. Test will fail if timeout exceeds 400 ms")
    public void BankAccountDTOClassHasNoNullFieldsOrProperties() throws Exception{

        assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/bank/accounts",
                BankAccountDTO.class)).hasNoNullFieldsOrProperties();
    }
}
