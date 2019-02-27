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

import static com.nayanzin.accountservice.entity.CreditCardType.MASTERCARD;


@RunWith(SpringRunner.class)
@JsonTest
public class CreditCardJsonTest {

    private static final String CREDIT_CARD_NUMBER = "1111-1111-1111-1111";

    private CreditCard creditCard;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JacksonTester<CreditCard> jacksonTester;

    @Before
    public void setUp() {
        creditCard = CreditCard
                .builder()
                .id(10L)
                .number(CREDIT_CARD_NUMBER)
                .type(MASTERCARD)
                .build();
        creditCard.setCreatedAt(12345L);
        creditCard.setLastModified(123456L);
    }

    @Test
    public void serializeCreditCardToJson() throws IOException {
        Assertions.assertThat(jacksonTester.write(creditCard)).isEqualTo("creditCard.json");
        Assertions.assertThat(jacksonTester.write(creditCard)).isEqualToJson("creditCard.json");
        Assertions.assertThat(jacksonTester.write(creditCard)).hasJsonPathStringValue("@.number");

        Assertions.assertThat(jacksonTester.write(creditCard)).extractingJsonPathStringValue("@.number")
                .isEqualTo(CREDIT_CARD_NUMBER);

        Assertions.assertThat(jacksonTester.write(creditCard)).extractingJsonPathStringValue("@.type")
                .isEqualTo("MASTERCARD");
    }

    @Test
    public void deserializeJsonToCreditCard() throws IOException {
        String content = "{\n" +
                "        \"createdAt\": 12345,\n" +
                "        \"lastModified\": 123456,\n" +
                "        \"id\": 10,\n" +
                "        \"number\": \"1111-1111-1111-1111\",\n" +
                "        \"type\": \"MASTERCARD\"\n" +
                "}";

        Assertions.assertThat(jacksonTester.parse(content)).isEqualTo(creditCard);
        Assertions.assertThat(jacksonTester.parseObject(content).getNumber())
                .isEqualTo(CREDIT_CARD_NUMBER);
    }
}