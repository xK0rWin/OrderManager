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
@DiscriminatorValue("WEINSCHORLE_ROT")
public class WeinschorleRot extends Drink {

    public WeinschorleRot() {
        super(Price.WEINSCHORLE_ROT);
    }

    public WeinschorleRot(Integer amount) {
        super(Price.WEINSCHORLE_ROT, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEINSCHORLE_ROT;
    }
}
