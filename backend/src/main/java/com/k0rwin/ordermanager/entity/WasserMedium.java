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
@DiscriminatorValue("WASSER_MEDIUM")
public class WasserMedium extends Drink {

    public WasserMedium() {
        super(Price.WASSER_MEDIUM);
    }

    public WasserMedium(Integer amount) {
        super(Price.WASSER_MEDIUM, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WASSER_MEDIUM;
    }
}
