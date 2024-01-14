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
@DiscriminatorValue("WILLIAMSCHRIST")
public class WilliamsChrist extends Drink {

    public WilliamsChrist() {
        super(Price.WILLIAMSCHRIST);
    }

    public WilliamsChrist(Integer amount) {
        super(Price.WILLIAMSCHRIST, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WILLIAMSCHRIST;
    }
    @Override
    public String getCategory() {
        return Category.SPIRITUOSEN;
    }
}
