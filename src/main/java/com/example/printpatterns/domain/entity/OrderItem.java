package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue
    private Long OrderItemId;

    @ManyToOne
    private Product product;
    private int quantity;

    OrderItem(Product p, int q){
        product = p;
        quantity = q;
    }

    void incrementQuantity(){
        this.quantity++;
    }

    void decrementQuantity(){
        assert quantity > 0;
        this.quantity--;
    }

    @Transient
    public double getSubTotal(){
        return quantity * product.getPrice();
    }


}
