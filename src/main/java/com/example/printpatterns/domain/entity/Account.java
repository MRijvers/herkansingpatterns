package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue
    private long accountId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String emailAddress;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private User userAccount;
}