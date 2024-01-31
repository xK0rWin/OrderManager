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
@DiscriminatorValue("WEINSCHORLE_WEISS_SAUER")
public class WeinschorleWeissSauer extends Drink {

    public WeinschorleWeissSauer() {
        super(Price.WEINSCHORLE_WEISS_SAUER);
    }

    public WeinschorleWeissSauer(Integer amount) {
        super(Price.WEINSCHORLE_WEISS_SAUER, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEINSCHORLE_WEISS_SAUER;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
