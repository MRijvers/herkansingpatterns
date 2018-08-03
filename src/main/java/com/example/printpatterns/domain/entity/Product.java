package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product implements Serializable
{
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int stock;
    private String category;
    private String colour;

    public void updateEditableFields(Product product) {
        productName = product.productName;
        description = product.description;
        price = product.price;
        stock = product.stock;
        category = product.category;
        colour = product.colour;
    }
}
