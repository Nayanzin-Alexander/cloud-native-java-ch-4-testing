package com.nayanzin.accountservice.exeption;

public class AccountNotFoundError extends Error {

    public AccountNotFoundError(String message) {
        super(message);
    }
}
