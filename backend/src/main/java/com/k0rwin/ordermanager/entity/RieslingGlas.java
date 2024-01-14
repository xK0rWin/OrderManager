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
@DiscriminatorValue("RIESLING_GLAS")
public class RieslingGlas extends Drink {

    public RieslingGlas() {
        super(Price.RIESLING_GLAS);
    }

    public RieslingGlas(Integer amount) {
        super(Price.RIESLING_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RIESLING_GLAS;
    }
}
