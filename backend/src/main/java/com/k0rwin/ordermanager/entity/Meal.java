package com.k0rwin.ordermanager.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.k0rwin.ordermanager.deserializer.MealDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@JsonDeserialize(using = MealDeserializer.class)
public abstract class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "amountOpen")
    private Integer amountOpen;

    public Meal() {}

    public Meal(Double price) {
        this.price = price;
        this.amount = 0;
        this.amountOpen = 0;
    }

    public Meal(Double price, Integer amount) {
        this.price = price;
        this.amount = amount;
        this.amountOpen = amount;
    }

    public abstract String getIdentifier();
}
