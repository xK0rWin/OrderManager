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
@DiscriminatorValue("SPAETB_WALD_MILD_GLAS")
public class WaldulmerMildGlas extends Drink {

    public WaldulmerMildGlas() {
        super(Price.SPAETB_WALD_MILD_GLAS);
    }

    public WaldulmerMildGlas(Integer amount) {
        super(Price.SPAETB_WALD_MILD_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_WALD_MILD_GLAS;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
