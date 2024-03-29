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
@DiscriminatorValue("GRAUBURGUNDER_FL")
public class GrauburgunderFlasche extends Drink {

    public GrauburgunderFlasche() {
        super(Price.GRAUBURGUNDER_FL);
    }

    public GrauburgunderFlasche(Integer amount) {
        super(Price.GRAUBURGUNDER_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.GRAUBURGUNDER_FL;
    }
    @Override
    public String getCategory() {
        return Category.WINE;
    }
}
