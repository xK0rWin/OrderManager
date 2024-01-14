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
@DiscriminatorValue("WASSER_STILL")
public class WasserStill extends Drink {

    public WasserStill() {
        super(Price.WASSER_STILL);
    }

    public WasserStill(Integer amount) {
        super(Price.WASSER_STILL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WASSER_STILL;
    }
}
