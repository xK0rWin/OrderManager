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
@DiscriminatorValue("BAUERNWUERSTE")
public class Bauernwuerste extends Meal {

    public Bauernwuerste() {
        super(Price.BAUERNWUERSTE);
    }

    public Bauernwuerste(Integer amount) {
        super(Price.BAUERNWUERSTE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.BAUERNWUERSTE;
    }
}
