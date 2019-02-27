package com.nayanzin.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class AccountNumber {

    @SuppressWarnings("{squid:S1700}")
    @Size(min = 9, max = 9)
    private String accountNumber;

    public AccountNumber(@Size(min = 9, max = 9) String accountNumber) {
        Assert.notNull(accountNumber, "Account Number must not be null");
        Assert.isTrue(accountNumber.length() == 9, "Account number must be exactly 9 characters");
        this.accountNumber = accountNumber;
    }

    @JsonValue
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return accountNumber;
    }
}
