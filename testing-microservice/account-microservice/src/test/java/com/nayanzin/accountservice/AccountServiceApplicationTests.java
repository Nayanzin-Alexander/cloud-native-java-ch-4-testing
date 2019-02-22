package com.nayanzin.accountservice;

import com.nayanzin.accountservice.entity.User;
import com.nayanzin.accountservice.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceApplicationTests {

    @MockBean
    private UserService userService;

    @Test
    public void userServiceShouldReturnMockResponse() {
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Dylan");

        given(this.userService.getAuthenticatedUser()).willReturn(user);

        User actual = userService.getAuthenticatedUser();
        assertThat(actual).isNotNull();
        assertThat(actual.getFirstName()).isEqualTo("Bob");
        assertThat(actual.getLastName()).isEqualTo("Dylan");
    }

}

