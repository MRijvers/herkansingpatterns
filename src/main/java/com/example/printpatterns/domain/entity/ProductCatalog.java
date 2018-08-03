package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductCatalog {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    List<Product> products = new LinkedList<>();

    public void addProduct(Product p){
        products.add(p);
    }

    public Product findProduct(Long id){
        for(Product p : products){
            if(p.getProductId() == id)
                return p;
            }
            return null;
    }

}
