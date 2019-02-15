package com.nayanzin.accountservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class CreditCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String number;

    @Enumerated(STRING)
    private CreditCardType type;
}
