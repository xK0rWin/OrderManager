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
@DiscriminatorValue("HEXECCO_GLAS")
public class HexeccoGlas extends Drink {

    public HexeccoGlas() {
        super(Price.HEXECCO_GLAS);
    }

    public HexeccoGlas(Integer amount) {
        super(Price.HEXECCO_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.HEXECCO_GLAS;
    }
}
