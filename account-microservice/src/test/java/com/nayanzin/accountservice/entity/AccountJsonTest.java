package com.nayanzin.accountservice.entity;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.nayanzin.accountservice.entity.AddressType.BILLING;
import static com.nayanzin.accountservice.entity.CreditCardType.MASTERCARD;


@RunWith(SpringRunner.class)
@JsonTest
public class AccountJsonTest {

    private static final String ACCOUNT_NUMBER = "123456789";

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JacksonTester<Account> jacksonTester;

    private Account account;

    @Before
    public void setUp() {
        // Generate credit card
        CreditCard creditCard = CreditCard.builder()
                .id(10L)
                .number("1111-1111-1111-1111")
                .type(MASTERCARD)
                .build();
        creditCard.setCreatedAt(12345L);
        creditCard.setLastModified(123456L);

        // Generate address
        Address address = Address.builder()
                .id(20L)
                .street1("Street1")
                .street2("Street2")
                .state("CA")
                .city("San Francisco")
                .country("US")
                .zipCode(94110)
                .addressType(BILLING)
                .build();
        address.setCreatedAt(12345L);
        address.setLastModified(123456L);

        // Generate account
        account = Account
                .builder()
                .id(1L)
                .username("username")
                .accountNumber(new AccountNumber(ACCOUNT_NUMBER))
                .defaultAccount(true)
                .creditCard(creditCard)
                .address(address)
                .build();
        account.setCreatedAt(12345L);
        account.setLastModified(12346L);
    }

    @Test
    public void serializeAccountToJson() throws IOException {
        Assertions.assertThat(jacksonTester.write(account)).isEqualTo("account.json");
        Assertions.assertThat(jacksonTester.write(account)).isEqualToJson("account.json");
        Assertions.assertThat(jacksonTester.write(account)).hasJsonPathStringValue("@.username");

        Assertions.assertThat(jacksonTester.write(account)).extractingJsonPathStringValue("@.username")
                .isEqualTo("username");

        Assertions.assertThat(jacksonTester.write(account)).extractingJsonPathStringValue("@.accountNumber")
                .isEqualTo(ACCOUNT_NUMBER);
    }

    @Test
    public void deserializeJsonToAccount() throws IOException {
        String content = "{\"username\": \"user\", \"accountNumber\": \"123456789\"}";

        Account actual = jacksonTester.parse(content).getObject();
        Assertions.assertThat(actual.getUsername()).isEqualTo("user");
        Assertions.assertThat(actual.getAccountNumber().getAccountNumber()).isEqualTo(ACCOUNT_NUMBER);
    }
}