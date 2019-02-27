package com.nayanzin.accountservice.entity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class AccountNumberTest {

    private static final String ACCOUNT_NUMBER = "123456789";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void createWhenAccountNumberIsNullShouldThrowException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account Number must not be null");
        new AccountNumber(null);
    }

    @Test
    public void createWhenAccountNumberIsMoreThan9CharsShouldThrowException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account number must be exactly 9 characters");
        new AccountNumber("1234567890");
    }

    @Test
    public void createWhenAccountNumberIsLessThan9CharsShouldThrowException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account number must be exactly 9 characters");
        new AccountNumber("12345678");
    }

    @Test
    public void toStringShouldReturnAccountNumber() {
        AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
        assertThat(accountNumber.toString(), equalTo(ACCOUNT_NUMBER));
    }

    @Test
    public void equalsAndHashCodeShouldBeBasedOnAccountNumber() {
        AccountNumber accountNumber1 = new AccountNumber(ACCOUNT_NUMBER);
        AccountNumber accountNumber2 = new AccountNumber(ACCOUNT_NUMBER);
        AccountNumber accountNumber3 = new AccountNumber("000000000");
        assertThat(accountNumber1.hashCode(), equalTo(accountNumber2.hashCode()));
        assertThat(accountNumber1, equalTo(accountNumber1));
        assertThat(accountNumber1, equalTo(accountNumber2));
        assertThat(accountNumber1, not(equalTo(accountNumber3)));
    }
}