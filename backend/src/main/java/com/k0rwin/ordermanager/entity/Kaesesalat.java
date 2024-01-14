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
@DiscriminatorValue("KAESESALAT")
public class Kaesesalat extends Meal {

    public Kaesesalat() {
        super(Price.KAESESALAT);
    }

    public Kaesesalat(Integer amount) {
        super(Price.KAESESALAT, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KAESESALAT;
    }
}
