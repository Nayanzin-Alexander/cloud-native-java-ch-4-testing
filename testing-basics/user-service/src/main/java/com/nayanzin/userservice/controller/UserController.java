package com.nayanzin.userservice.controller;

import com.nayanzin.userservice.entity.User;
import com.nayanzin.userservice.service.AuthService;
import com.nayanzin.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/uaa/v1")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(Principal principal) {

        // old style

        Principal principal1 = authService.getAuthenticatedUser(principal);

        if (principal1 == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userService.getUserByPrincipal(principal1);
        return isNull(user) ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
                : ResponseEntity.ok(user);

/*
        return Optional.ofNullable(
                authService.getAuthenticatedUser(principal))
                .map(p -> ResponseEntity.ok(userService.getUserByPrincipal(p)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                */
    }
}
