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
@DiscriminatorValue("SPAETBURGUNDER_WALD_MILD_FL")
public class SpaetburgunderWaldulmerMildFl extends Drink {

    public SpaetburgunderWaldulmerMildFl() {
        super(Price.SPAETB_WALD_MILD_FL);
    }

    public SpaetburgunderWaldulmerMildFl(Integer amount) {
        super(Price.SPAETB_WALD_MILD_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_WALD_MILD_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
