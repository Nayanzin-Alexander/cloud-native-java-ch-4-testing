package com.nayanzin.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;
}
