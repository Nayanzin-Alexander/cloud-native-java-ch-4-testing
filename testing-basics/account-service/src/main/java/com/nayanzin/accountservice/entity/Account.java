package com.nayanzin.accountservice.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String username;

    @Embedded
    private AccountNumber accountNumber;

    private Boolean defaultAccount;

    @OneToMany(cascade = ALL, fetch = EAGER)
    private Set<CreditCard> creditCards;

    @OneToMany(cascade = ALL, fetch = EAGER)
    private Set<Address> addresses;
}
