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
@DiscriminatorValue("RIESLING_FL")
public class RieslingFl extends Drink {

    public RieslingFl() {
        super(Price.RIESLING_FL);
    }

    public RieslingFl(Integer amount) {
        super(Price.RIESLING_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RIESLING_FL;
    }
}
