package com.k0rwin.ordermanager.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.k0rwin.ordermanager.deserializer.DrinkDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonDeserialize(using = DrinkDeserializer.class)
public abstract class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "amount")
    private Integer amount;

    public Drink(){}

    public Drink(Double price) {
        this.price = price;
        this.amount = 0;
    }

    public Drink(Double price, Integer amount) {
        this.price = price;
        this.amount = amount;
    }

    public abstract String getIdentifier();
    public abstract String getCategory();
}
