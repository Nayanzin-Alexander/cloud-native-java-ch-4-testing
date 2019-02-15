package com.nayanzin.userservice.service;

import org.springframework.stereotype.Service;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;

import static java.util.Objects.isNull;

@Service
public class AuthService {
    public Principal getAuthenticatedUser(Principal principal) {
        return isNull(principal) ? new PrincipalImpl("user") : principal;
    }
}
