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
@DiscriminatorValue("VESPERPLATTE")
public class Vesperplatte extends Meal {

    public Vesperplatte() {
        super(Price.VESPERPLATTE);
    }

    public Vesperplatte(Integer amount) {
        super(Price.VESPERPLATTE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.VESPERPLATTE;
    }
}
