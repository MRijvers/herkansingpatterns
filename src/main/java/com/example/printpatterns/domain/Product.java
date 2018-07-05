package com.example.printpatterns.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product
{
    private long productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String type;
    private String colour;
}
