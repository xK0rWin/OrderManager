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
@DiscriminatorValue("SPAETB_KA_TROCKEN_FL")
public class SpaetburgunderHexTrockenFl extends Drink {

    public SpaetburgunderHexTrockenFl() {
        super(Price.SPAETB_KA_TROCKEN_FL);
    }

    public SpaetburgunderHexTrockenFl(Integer amount) {
        super(Price.SPAETB_KA_TROCKEN_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_KA_TROCKEN_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
