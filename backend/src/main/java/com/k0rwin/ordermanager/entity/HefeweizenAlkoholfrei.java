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
@DiscriminatorValue("HEFEWEIZEN_ALKFREI")
public class HefeweizenAlkoholfrei extends Drink {

    public HefeweizenAlkoholfrei() {
        super(Price.HEFEWEIZEN_ALKFREI);
    }

    public HefeweizenAlkoholfrei(Integer amount) {
        super(Price.HEFEWEIZEN_ALKFREI, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.HEFEWEIZEN_ALKFREI;
    }
}

