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
@DiscriminatorValue("SPAETB_WALD_TROCKEN_GLAS")
public class SpaetburgunderWaldulmerTrockenGlas extends Drink {

    public SpaetburgunderWaldulmerTrockenGlas() {
        super(Price.SPAETB_WALD_TROCKEN_GLAS);
    }

    public SpaetburgunderWaldulmerTrockenGlas(Integer amount) {
        super(Price.SPAETB_WALD_TROCKEN_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_WALD_TROCKEN_GLAS;
    }
}
