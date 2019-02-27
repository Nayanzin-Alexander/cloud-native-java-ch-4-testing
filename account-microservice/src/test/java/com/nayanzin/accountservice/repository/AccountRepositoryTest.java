package com.nayanzin.accountservice.repository;

import com.nayanzin.accountservice.entity.Account;
import com.nayanzin.accountservice.entity.AccountNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    private static final String ACCOUNT_NUMBER = "123456700";
    private static final String NOT_EXISTENT_ACCOUNT_NUMBER = "987654321";
    private static final String USERNAME = "username";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
        Account account = Account
                .builder()
                .accountNumber(new AccountNumber(ACCOUNT_NUMBER))
                .username(USERNAME)
                .build();
        entityManager.persist(account);
    }

    @Test
    public void findAccountByUsernameShouldReturnUserAccount() {
        // Find
        List<Account> actualAccounts = accountRepository.findAccountByUsername(USERNAME);

        // Assert
        assertThat(actualAccounts.size(), is(1));
        Account actual = actualAccounts.get(0);
        assertThat(actual.getUsername(), is(USERNAME));
        assertThat(actual.getAccountNumber().getAccountNumber(), is(ACCOUNT_NUMBER));
    }

    @Test
    public void findAccountByAccountNumberShouldReturnUserAccount() {
        // Find
        Account account = accountRepository.findByAccountNumber(new AccountNumber(ACCOUNT_NUMBER));

        // Assert
        assertThat(account.getUsername(), is(USERNAME));
        assertThat(account.getAccountNumber().getAccountNumber(), is(ACCOUNT_NUMBER));
    }

    // find user account by account number if not exists should return null
    @Test
    public void findAccountByNotExistentAccountNumberShouldReturnNull() {
        // Find
        Account account = accountRepository.findByAccountNumber(new AccountNumber(NOT_EXISTENT_ACCOUNT_NUMBER));

        // Assert
        assertThat(account, nullValue());
    }

}