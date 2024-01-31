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
@DiscriminatorValue("WEINSCHORLE_ROT_SAUER")
public class WeinschorleRotSauer extends Drink {

    public WeinschorleRotSauer() {
        super(Price.WEINSCHORLE_ROT_SAUER);
    }

    public WeinschorleRotSauer(Integer amount) {
        super(Price.WEINSCHORLE_ROT_SAUER, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEINSCHORLE_ROT_SAUER;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
