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
@DiscriminatorValue("SALATVARIATION")
public class Salatvariation extends Meal {

    public Salatvariation() {
        super(Price.SALATVARIATION);
    }

    public Salatvariation(Integer amount) {
        super(Price.SALATVARIATION, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SALATVARIATION;
    }
}
