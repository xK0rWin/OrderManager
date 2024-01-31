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
@DiscriminatorValue("MUELLER_THURGAU_GLAS")
public class MuellerGlas extends Drink {

    public MuellerGlas() {
        super(Price.MUELLER_THURGAU_GLAS);
    }

    public MuellerGlas(Integer amount) {
        super(Price.MUELLER_THURGAU_GLAS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.MUELLER_THURGAU_GLAS;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
