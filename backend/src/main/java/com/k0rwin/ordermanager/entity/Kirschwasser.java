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
@DiscriminatorValue("KIRSCHWASSER")
public class Kirschwasser extends Drink {

    public Kirschwasser() {
        super(Price.KIRSCHWASSER);
    }

    public Kirschwasser(Integer amount) {
        super(Price.KIRSCHWASSER, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KIRSCHWASSER;
    }
}
