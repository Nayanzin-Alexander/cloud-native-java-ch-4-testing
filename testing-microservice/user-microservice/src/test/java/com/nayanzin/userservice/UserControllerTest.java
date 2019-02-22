package com.nayanzin.userservice;

import com.nayanzin.userservice.controller.UserController;
import com.nayanzin.userservice.entity.User;
import com.nayanzin.userservice.service.AuthService;
import com.nayanzin.userservice.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;
import sun.security.acl.PrincipalImpl;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Value("classpath:com/nayanzin/userservice/controller/user.json")
    private Resource userJson;

    private static String userJsonString;

    @Before
    public void initTest() throws IOException {
        userJsonString = StreamUtils.copyToString(userJson.getInputStream(), Charset.defaultCharset());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthService authService;

    @Test
    public void getUserShouldReturnUser() throws Exception {

        User user = new User(55L, "user0", "Jack0", "Frost0", "jfrost0@example.com");
        user.setCreatedAt(123450L);
        user.setLastModified(1234660L);

        given(userService.getUserByPrincipal(new PrincipalImpl("user")))
                .willReturn(user);

        given(authService.getAuthenticatedUser(null))
                .willReturn(new PrincipalImpl("user"));

        this.mockMvc
                .perform(get("/uaa/v1/me")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(userJsonString));
    }
}