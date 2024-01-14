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
@DiscriminatorValue("SPAETB_WALD_TROCKEN_FL")
public class SpaetburgunderWaldulmerTrockenFl extends Drink {

    public SpaetburgunderWaldulmerTrockenFl() {
        super(Price.SPAETB_WALD_TROCKEN_FL);
    }

    public SpaetburgunderWaldulmerTrockenFl(Integer amount) {
        super(Price.SPAETB_WALD_TROCKEN_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_WALD_TROCKEN_FL;
    }
}
