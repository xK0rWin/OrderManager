package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.bundle.Category;
import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.bundle.Price;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("WEINSCHORLE_WEISS")
public class WeinschorleWeiss extends Drink {

    public WeinschorleWeiss() {
        super(Price.WEINSCHORLE_WEISS);
    }

    public WeinschorleWeiss(Integer amount) {
        super(Price.WEINSCHORLE_WEISS, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEINSCHORLE_WEISS;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
