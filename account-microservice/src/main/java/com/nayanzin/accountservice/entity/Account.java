package com.nayanzin.accountservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String username;

    @Embedded
    private AccountNumber accountNumber;

    private Boolean defaultAccount;

    @Singular
    @OneToMany(cascade = ALL, fetch = EAGER)
    private Set<CreditCard> creditCards;

    @Singular
    @OneToMany(cascade = ALL, fetch = EAGER)
    private Set<Address> addresses;
}
