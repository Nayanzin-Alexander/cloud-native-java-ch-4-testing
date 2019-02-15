package com.nayanzin.accountservice.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Data
@Embeddable
public class AccountNumber {

    @Size(min = 9, max = 9)
    private String accountNumber;
}
