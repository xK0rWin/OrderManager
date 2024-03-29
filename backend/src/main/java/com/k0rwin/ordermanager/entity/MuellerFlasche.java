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
@DiscriminatorValue("MUELLER_THURGAU_FL")
public class MuellerFlasche extends Drink {

    public MuellerFlasche() {
        super(Price.MUELLER_THURGAU_FL);
    }

    public MuellerFlasche(Integer amount) {
        super(Price.MUELLER_THURGAU_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.MUELLER_THURGAU_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
