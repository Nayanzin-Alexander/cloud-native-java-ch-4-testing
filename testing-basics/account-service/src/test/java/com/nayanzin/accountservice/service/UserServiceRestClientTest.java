package com.nayanzin.accountservice.service;

import com.nayanzin.accountservice.entity.User;
import com.nayanzin.accountservice.exeption.UserNotFoundError;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringRunner.class)
@RestClientTest({UserService.class})
@AutoConfigureWebClient(registerRestTemplate = true)
public class UserServiceRestClientTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final String USERS_ME = "/uaa/v1/me";

    @Value("http://" + "${user-service.host:user-service}")
    private String serviceHost;

    @Autowired
    private UserService userService;

    @Autowired
    private MockRestServiceServer server;

    private Resource userJson;

    @Before
    public void setUp() {
        userJson = new ClassPathResource("user.json", getClass());
    }

    // test 200
    @Test
    public void getAuthenticatedUserReturnsUser() {

        // Setup mock server.
        RequestMatcher request = requestTo(serviceHost + USERS_ME);
        ResponseCreator respond200Ok = MockRestResponseCreators.withSuccess(userJson, APPLICATION_JSON);
        server.expect(request).andRespond(respond200Ok);

        // When
        User actual = userService.getAuthenticatedUser();

        // Then
        assertThat(actual.getUsername(), is("WillSmith"));
        assertThat(actual.getFirstName(), is("Will"));
        assertThat(actual.getLastName(), is("Smith"));
        assertThat(actual.getCreatedAt(), is(111L));
        assertThat(actual.getLastModified(), is(222L));
        assertThat(actual.getId(), is(100L));
    }

    // test 404
    @Test
    public void getAuthenticatedUserReturnsNotFound() {

        // Setup mock server.
        RequestMatcher request = requestTo(serviceHost + USERS_ME);
        ResponseCreator respond404NotFount = MockRestResponseCreators.withStatus(NOT_FOUND);
        server.expect(request).andRespond(respond404NotFount);

        // Expect exception
        thrown.expect(UserNotFoundError.class);
        thrown.expectMessage("UserService respond with 404 not found.");

        // When
        userService.getAuthenticatedUser();
    }

    // test 500
    @Test
    public void getAuthenticatedUserReturnsInternalServerError() {

        // Setup mock server.
        RequestMatcher request = requestTo(serviceHost + USERS_ME);
        ResponseCreator respond500InternalServerError = MockRestResponseCreators.withStatus(INTERNAL_SERVER_ERROR);
        server.expect(request).andRespond(respond500InternalServerError);

        // Expect exception
        thrown.expect(UserNotFoundError.class);
        thrown.expectMessage("UserService respond with 500 Internal Server Error.");

        // When
        userService.getAuthenticatedUser();
    }
}