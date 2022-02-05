package com.example.julian.test.serviceApi.entities;

import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;

    public Product(){

    }

    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    @PersistenceConstructor
    public Product(
            Long id,
            String name,
            BigDecimal price
    ){
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
