package com.nayanzin.controller;

import com.nayanzin.userservice.controller.UserController;
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
public class ContractVerifierBase {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Before
    public void setup() {
        User user = new User(55L, "user0", "Jack0", "Frost0", "jfrost0@example.com");
        user.setCreatedAt(123450L);
        user.setLastModified(1234660L);

        given(userService.getUserByPrincipal(new PrincipalImpl("user")))
                .willReturn(user);

        given(authService.getAuthenticatedUser(null))
                .willReturn(new PrincipalImpl("user"));

        RestAssuredMockMvc.standaloneSetup(new UserController(userService,
                authService));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}
