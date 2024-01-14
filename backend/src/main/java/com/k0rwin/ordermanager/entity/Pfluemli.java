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
@DiscriminatorValue("PFLUEMLI")
public class Pfluemli extends Drink {

    public Pfluemli() {
        super(Price.PFLUEMLI);
    }

    public Pfluemli(Integer amount) {
        super(Price.PFLUEMLI, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.PFLUEMLI;
    }
}
