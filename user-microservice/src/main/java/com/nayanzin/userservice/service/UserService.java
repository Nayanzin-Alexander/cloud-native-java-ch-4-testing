package com.nayanzin.userservice.service;

import com.nayanzin.userservice.entity.User;
import com.nayanzin.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPrincipal(Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> userRepository.findUserByUsername(p.getName()))
                .orElse(null);
    }
}
