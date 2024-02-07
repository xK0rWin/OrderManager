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
@DiscriminatorValue("COLAWEIZEN")
public class Colaweizen extends Drink {

    public Colaweizen() {
        super(Price.COLAWEIZEN);
    }

    public Colaweizen(Integer amount) {
        super(Price.COLAWEIZEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.COLAWEIZEN;
    }
    @Override
    public String getCategory() {
        return Category.BEER;
    }
}

