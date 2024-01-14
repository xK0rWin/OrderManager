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
@DiscriminatorValue("JACKY_COLA")
public class JackyCola extends Drink {

    public JackyCola() {
        super(Price.JACKY_COLA);
    }

    public JackyCola(Integer amount) {
        super(Price.JACKY_COLA, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.JACKY_COLA;
    }
}
