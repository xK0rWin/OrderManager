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
@DiscriminatorValue("KARTOFFELSUPPE_WIENERLE")
public class KartoffelsuppeMitWienerle extends Meal {

    public KartoffelsuppeMitWienerle() {
        super(Price.KARTOFFELSUPPE_WIENERLE);
    }

    public KartoffelsuppeMitWienerle(Integer amount) {
        super(Price.KARTOFFELSUPPE_WIENERLE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KARTOFFELSUPPE_WIENERLE;
    }
}
