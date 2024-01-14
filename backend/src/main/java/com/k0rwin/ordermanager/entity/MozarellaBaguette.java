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
@DiscriminatorValue("MOZARELLA_BAGUETTE")
public class MozarellaBaguette extends Meal {

    public MozarellaBaguette() {
        super(Price.MOZARELLA_BAGUETTE);
    }

    public MozarellaBaguette(Integer amount) {
        super(Price.MOZARELLA_BAGUETTE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.MOZARELLA_BAGUETTE;
    }
}
