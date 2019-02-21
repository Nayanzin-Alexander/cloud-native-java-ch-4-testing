package com.nayanzin.accountservice.controller;

import com.nayanzin.accountservice.entity.Account;
import com.nayanzin.accountservice.exeption.AccountNotFoundError;
import com.nayanzin.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getUserAccounts() {
        return Optional.ofNullable(accountService.getUserAccounts())
                .map(a -> new ResponseEntity<>(a, OK))
                .orElseThrow(() -> new AccountNotFoundError("Accounts do not exist."));
    }

}
