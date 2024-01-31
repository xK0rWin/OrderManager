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
@DiscriminatorValue("HEXECCO_FL")
public class HexeccoFlasche extends Drink {

    public HexeccoFlasche() {
        super(Price.HEXECCO_FL);
    }

    public HexeccoFlasche(Integer amount) {
        super(Price.HEXECCO_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.HEXECCO_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
