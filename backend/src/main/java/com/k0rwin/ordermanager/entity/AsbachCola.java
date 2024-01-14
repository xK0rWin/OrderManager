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
@DiscriminatorValue("ASBACH_COLA")
public class AsbachCola extends Drink {

    public AsbachCola() {
        super(Price.ASBACH_COLA);
    }

    public AsbachCola(Integer amount) {
        super(Price.ASBACH_COLA, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.ASBACH_COLA;
    }
}
