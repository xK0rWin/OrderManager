package com.k0rwin.ordermanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.bundle.Price;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("COKE")
public class Coke extends Drink {

    public Coke() {
        super();
    }

    public Coke(Order order) {
        super(order, Price.COKE);
    }

    @Override
    public String getIdentifier() {
        return Identifier.COKE;
    }
}
