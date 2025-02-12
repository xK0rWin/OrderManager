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
@DiscriminatorValue("SUPPE_GULASCH")
public class SuppeGulasch extends Meal {

    public SuppeGulasch() {
        super(Price.GULASCHSUPPE);
    }

    public SuppeGulasch(Integer amount) {
        super(Price.GULASCHSUPPE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.GULASCHSUPPE;
    }
}
