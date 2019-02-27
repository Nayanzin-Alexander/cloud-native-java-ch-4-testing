package com.nayanzin.accountservice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Long createdAt;

    private Long lastModified;
}
