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
@DiscriminatorValue("APFELSCHORLE")
public class Apfelschorle extends Drink {

    public Apfelschorle() {
        super(Price.APFELSCHORLE);
    }

    public Apfelschorle(Integer amount) {
        super(Price.APFELSCHORLE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.APFELSCHORLE;
    }
}
