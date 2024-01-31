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
@DiscriminatorValue("RIESLING_FL")
public class RieslingFlasche extends Drink {

    public RieslingFlasche() {
        super(Price.RIESLING_FL);
    }

    public RieslingFlasche(Integer amount) {
        super(Price.RIESLING_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.RIESLING_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
