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
public class SuppeWienerle extends Meal {

    public SuppeWienerle() {
        super(Price.KARTOFFELSUPPE_WIENERLE);
    }

    public SuppeWienerle(Integer amount) {
        super(Price.KARTOFFELSUPPE_WIENERLE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KARTOFFELSUPPE_WIENERLE;
    }
}
