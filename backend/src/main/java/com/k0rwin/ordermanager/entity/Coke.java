package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.bundle.Price;
import lombok.Getter;
import lombok.Setter;

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

    public Coke(Integer amount) {
        super(Price.COKE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.COKE;
    }
}
