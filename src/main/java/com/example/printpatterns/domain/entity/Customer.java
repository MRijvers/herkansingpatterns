package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;

//@Entity
@Getter
@Setter
public class Customer extends Account {
    @Id
    @GeneratedValue
    private long customerId;

    @OneToMany
    private User User;

    @OneToMany(cascade = CascadeType.ALL)
    private LinkedList<Order> customerOrders;
}
