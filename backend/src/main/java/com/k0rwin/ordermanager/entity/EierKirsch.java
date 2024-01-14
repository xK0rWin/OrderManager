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
@DiscriminatorValue("EIERKIRSCH")
public class EierKirsch extends Drink {

    public EierKirsch() {
        super(Price.EIERKIRSCH);
    }

    public EierKirsch(Integer amount) {
        super(Price.EIERKIRSCH, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.EIERKIRSCH;
    }
}
