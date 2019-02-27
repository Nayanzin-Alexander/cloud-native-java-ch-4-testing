package com.nayanzin.accountservice.service;

import com.nayanzin.accountservice.entity.Account;
import com.nayanzin.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public List<Account> getUserAccounts() {
        return Optional.ofNullable(userService.getAuthenticatedUser())
                .map(u -> accountRepository.findAccountByUsername(u.getUsername()))
                .orElse(emptyList());
    }
}
