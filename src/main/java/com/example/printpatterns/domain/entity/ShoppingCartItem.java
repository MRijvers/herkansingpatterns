package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
@NoArgsConstructor
public class ShoppingCartItem implements Serializable {

    private Product product;
    private int quantity;

    public ShoppingCartItem(Product p){
        product = p;
        quantity = 1;
    }

    public void incrementQuantity(){
        quantity++;
    }

    public void decrementQuantity(){
        --quantity;
    }

    public double getTotal(){
        double total = 0;
        total = (this.getQuantity() * product.getPrice());
        return total;
    }


}
