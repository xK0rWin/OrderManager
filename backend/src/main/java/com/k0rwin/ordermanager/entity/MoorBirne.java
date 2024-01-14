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
@DiscriminatorValue("MOORBIRNE")
public class MoorBirne extends Drink {

    public MoorBirne() {
        super(Price.MOORBIRNE);
    }

    public MoorBirne(Integer amount) {
        super(Price.MOORBIRNE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.MOORBIRNE;
    }
}
