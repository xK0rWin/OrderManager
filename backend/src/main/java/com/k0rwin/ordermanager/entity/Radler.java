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
@DiscriminatorValue("RADLER")
public class Radler extends Drink {

    public Radler() {
        super(Price.RADLER);
    }

    public Radler(Integer amount) {
        super(Price.RADLER, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RADLER;
    }
}

