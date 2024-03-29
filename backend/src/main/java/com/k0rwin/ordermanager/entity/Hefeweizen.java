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
@DiscriminatorValue("HEFEWEIZEN")
public class Hefeweizen extends Drink {

    public Hefeweizen() {
        super(Price.HEFEWEIZEN);
    }

    public Hefeweizen(Integer amount) {
        super(Price.HEFEWEIZEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.HEFEWEIZEN;
    }
    @Override
    public String getCategory() {
        return Category.BEER;
    }
}

