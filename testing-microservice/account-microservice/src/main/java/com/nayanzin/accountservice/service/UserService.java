package com.nayanzin.accountservice.service;

import com.nayanzin.accountservice.entity.User;
import com.nayanzin.accountservice.exeption.UserNotFoundError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.RequestEntity.get;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final String userMeUri;

    @Autowired
    public UserService(RestTemplate restTemplate,
            @Value("${user-service.host:user-service}") String serviceHost) {
        this.restTemplate = restTemplate;
        this.userMeUri = UriComponentsBuilder.newInstance()
                .scheme("http").host(serviceHost)
                .path("/uaa/v1/me").build().toUriString();
    }

    public User getAuthenticatedUser() {
        RequestEntity<Void> request = get(URI.create(userMeUri)).header(CONTENT_TYPE, APPLICATION_JSON_VALUE).build();
        try {
            return restTemplate.exchange(request, User.class).getBody();
        } catch (HttpClientErrorException.NotFound notFound) {
            throw new UserNotFoundError("UserService respond with 404 not found.", notFound);
        } catch (HttpServerErrorException.InternalServerError internalServerError) {
            throw new UserNotFoundError("UserService respond with 500 Internal Server Error.", internalServerError);
        }
    }
}
