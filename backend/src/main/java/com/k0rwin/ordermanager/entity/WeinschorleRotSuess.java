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
@DiscriminatorValue("WEINSCHORLE_ROT")
public class WeinschorleRotSuess extends Drink {

    public WeinschorleRotSuess() {
        super(Price.WEINSCHORLE_ROT);
    }

    public WeinschorleRotSuess(Integer amount) {
        super(Price.WEINSCHORLE_ROT, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WEINSCHORLE_ROT;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
