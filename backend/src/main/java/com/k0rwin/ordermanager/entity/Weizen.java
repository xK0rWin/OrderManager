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
@DiscriminatorValue("WEIZEN")
public class Weizen extends Drink {

    public Weizen() {
        super(Price.WEIZEN);
    }

    public Weizen(Integer amount) {
        super(Price.WEIZEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEIZEN;
    }
}

