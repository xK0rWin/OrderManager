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
@DiscriminatorValue("PILS")
public class Pils extends Drink {

    public Pils() {
        super(Price.PILS);
    }

    public Pils(Integer amount) {
        super(Price.PILS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.PILS;
    }
}

