package com.nayanzin.accountservice;

import com.nayanzin.accountservice.entity.User;
import com.nayanzin.accountservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        ids = {"com.nayanzin:user-microservice:+:stubs:48080"},
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
public class ContractConsumerTest {

    @Autowired
    private UserService service;

    @Test
    public void shouldReturnAuthenticatedUsers() {
        User actual  = service.getAuthenticatedUser();
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).matches("(\\w){1,256}+");
        assertThat(actual.getFirstName()).matches("[A-Za-z]{1,256}+");
        assertThat(actual.getLastName()).matches("[A-Za-z]{1,256}+");
    }
}
