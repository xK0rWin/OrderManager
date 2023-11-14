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
@DiscriminatorValue("SPEZI")
public class Spezi extends Drink {

    public Spezi() {
        super(Price.SPEZI);
    }

    public Spezi(Integer amount) {
        super(Price.SPEZI, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPEZI;
    }
}
