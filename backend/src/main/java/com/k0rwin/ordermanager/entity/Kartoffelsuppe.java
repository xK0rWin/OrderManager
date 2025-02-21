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
@DiscriminatorValue("KARTOFFELSUPPE")
public class Kartoffelsuppe extends Meal {

    public Kartoffelsuppe() {
        super(Price.KARTOFFELSUPPE_VEG);
    }

    public Kartoffelsuppe(Integer amount) {
        super(Price.KARTOFFELSUPPE_VEG, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KARTOFFELSUPPE_VEG;
    }
}
