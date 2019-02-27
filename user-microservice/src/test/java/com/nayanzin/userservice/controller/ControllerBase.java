package com.nayanzin.userservice.controller;

import com.nayanzin.userservice.entity.User;
import com.nayanzin.userservice.service.AuthService;
import com.nayanzin.userservice.service.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.acl.PrincipalImpl;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class ControllerBase {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Before
    public void setup() {

        User actual = new User(55L, "user", "Jack", "Frost", "jfrost@example.com");
        actual.setCreatedAt(123450L);
        actual.setLastModified(1234660L);
        actual.setId(0L);
        given(this.userService.getUserByPrincipal(new PrincipalImpl("user")))
                .willReturn(actual);

        given(this.authService.getAuthenticatedUser(null)).willReturn(
                new PrincipalImpl("user"));

        RestAssuredMockMvc.standaloneSetup(new UserController(userService,
                authService));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}