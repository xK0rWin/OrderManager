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
@DiscriminatorValue("SCHINKENKRUSTI")
public class Schinkenkrusti extends Meal {

    public Schinkenkrusti() {
        super();
    }

    public Schinkenkrusti(Integer amount) {
        super(Price.KRUSTI_SCHINKEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KRUSTI_SCHINKEN;
    }
}
