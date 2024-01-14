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
@DiscriminatorValue("KARTOFFELSUPPE_VEG")
public class KartoffelsuppeMitBrot extends Meal {

    public KartoffelsuppeMitBrot() {
        super(Price.KARTOFFELSUPPE_VEG);
    }

    public KartoffelsuppeMitBrot(Integer amount) {
        super(Price.KARTOFFELSUPPE_VEG, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KARTOFFELSUPPE_VEG;
    }
}
