package com.nayanzin.accountservice.exeption;

public class UserNotFoundError extends Error {
    public UserNotFoundError(String detailMessage, Exception cause) {
        super(detailMessage, cause);
    }
}
