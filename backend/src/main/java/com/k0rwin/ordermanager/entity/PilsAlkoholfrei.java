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
@DiscriminatorValue("PILS_ALKFREI")
public class PilsAlkoholfrei extends Drink {

    public PilsAlkoholfrei() {
        super(Price.PILS_ALKFREI);
    }

    public PilsAlkoholfrei(Integer amount) {
        super(Price.PILS_ALKFREI, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.PILS_ALKFREI;
    }
    @Override
    public String getCategory() {
        return Category.BEER;
    }
}

