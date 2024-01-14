package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.bundle.Category;
import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.bundle.Price;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("SPAETB_KA_TROCKEN_GLAS")
public class SpaetburgunderHexTrockenGlas extends Drink {

    public SpaetburgunderHexTrockenGlas() {
        super(Price.SPAETB_KA_TROCKEN_GLAS);
    }

    public SpaetburgunderHexTrockenGlas(Integer amount) {
        super(Price.SPAETB_KA_TROCKEN_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_KA_TROCKEN_GLAS;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
