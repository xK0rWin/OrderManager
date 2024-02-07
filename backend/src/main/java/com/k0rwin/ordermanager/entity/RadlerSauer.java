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
@DiscriminatorValue("RADLER_SAUER")
public class RadlerSauer extends Drink {

    public RadlerSauer() {
        super(Price.RADLER_SAUER);
    }

    public RadlerSauer(Integer amount) {
        super(Price.RADLER_SAUER, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RADLER_SAUER;
    }
    @Override
    public String getCategory() {
        return Category.BEER;
    }
}

