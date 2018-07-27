package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product implements Serializable
{
    @Id
    @GeneratedValue
    private long productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String category;
    private String colour;
}
