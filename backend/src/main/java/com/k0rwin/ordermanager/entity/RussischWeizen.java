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
@DiscriminatorValue("RUSSISCHWEIZEN")
public class RussischWeizen extends Drink {

    public RussischWeizen() {
        super(Price.RUSSISCHWEIZEN);
    }

    public RussischWeizen(Integer amount) {
        super(Price.RUSSISCHWEIZEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RUSSISCHWEIZEN;
    }
    @Override
    public String getCategory() {
        return Category.BEER;
    }
}

