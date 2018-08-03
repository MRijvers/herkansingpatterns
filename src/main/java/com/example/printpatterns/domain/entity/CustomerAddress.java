package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CustomerAddress {

    @Id
    private long id;
    private String address;
    private String city;
    private String country;
    private String houseNumber;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User User;
}
