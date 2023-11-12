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
@DiscriminatorValue("BAGUETTE")
public class Baguette extends Meal {

    public Baguette() {
        super();
    }

    public Baguette(Integer amount) {
        super(Price.BAGUETTE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.BAGUETTE;
    }
}
