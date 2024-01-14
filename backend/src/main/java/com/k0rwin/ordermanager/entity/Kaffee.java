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
@DiscriminatorValue("KAFFEE")
public class Kaffee extends Drink {

    public Kaffee() {
        super(Price.KAFFEE);
    }

    public Kaffee(Integer amount) {
        super(Price.KAFFEE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KAFFEE;
    }
    @Override
    public String getCategory() {
        return Category.SOFTDRINKS;
    }
}
