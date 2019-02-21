package com.nayanzin.accountservice.service;

import com.nayanzin.accountservice.entity.Account;
import com.nayanzin.accountservice.entity.AccountNumber;
import com.nayanzin.accountservice.entity.User;
import com.nayanzin.accountservice.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    private static final String USERNAME = "username";
    private static final String ACCOUNT_NUMBER = "123456789";

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private UserService userService;

    @Mock
    private User user;

    private AccountService accountService;

    @Before
    public void setUp() {
        accountService = new AccountService(accountRepository, userService);
    }

    @Test
    public void getUserAccountsReturnsUserAccounts() {

        // Setup AccountService
        Account expected = Account.builder()
                .username(USERNAME)
                .accountNumber(new AccountNumber(ACCOUNT_NUMBER))
                .build();

        when(user.getUsername()).thenReturn(USERNAME);
        when(userService.getAuthenticatedUser()).thenReturn(user);
        when(accountRepository.findAccountByUsername(USERNAME)).thenReturn(Collections.singletonList(expected));

        // Invoke method
        List<Account> actualList = accountService.getUserAccounts();

        // Assert
        assertThat(actualList.size(), is(1));
        Account actual = actualList.get(0);
        assertThat(actual.getUsername(), is(USERNAME));
        assertThat(actual.getAccountNumber().getAccountNumber(), is(ACCOUNT_NUMBER));
    }
}