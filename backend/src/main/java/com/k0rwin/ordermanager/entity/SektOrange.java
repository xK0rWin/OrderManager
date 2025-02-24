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
@DiscriminatorValue("SEKTORANGE")
public class SektOrange extends Drink {

    public SektOrange() {
        super(Price.SEKTORANGE);
    }

    public SektOrange(Integer amount) {
        super(Price.SEKTORANGE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SEKTORANGE;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}

