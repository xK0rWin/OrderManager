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
@DiscriminatorValue("GRAUBURGUNDER_GLAS")
public class GrauburgunderTrockenGlas extends Drink {

    public GrauburgunderTrockenGlas() {
        super(Price.GRAUBURGUNDER_GLAS);
    }

    public GrauburgunderTrockenGlas(Integer amount) {
        super(Price.GRAUBURGUNDER_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.GRAUBURGUNDER_GLAS;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
