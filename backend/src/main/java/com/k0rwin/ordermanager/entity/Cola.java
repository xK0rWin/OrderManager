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
@DiscriminatorValue("COLA")
public class Cola extends Drink {

    public Cola() {
        super();
    }

    public Cola(Integer amount) {
        super(Price.COLA, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.COLA;
    }
}
