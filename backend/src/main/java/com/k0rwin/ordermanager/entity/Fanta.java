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
@DiscriminatorValue("FANTA")
public class Fanta extends Drink {

    public Fanta() {
        super(Price.FANTA);
    }

    public Fanta(Integer amount) {
        super(Price.FANTA, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.FANTA;
    }
    @Override
    public String getCategory() {
        return Category.SOFTDRINKS;
    }
}
