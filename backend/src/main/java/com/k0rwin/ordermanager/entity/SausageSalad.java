package com.k0rwin.ordermanager.entity;

import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.bundle.Price;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("SAUSAGE_SALAD")
public class SausageSalad extends Meal {

    public SausageSalad() {
        super();
    }

    public SausageSalad(Integer amount) {
        super(Price.SAUSAGE_SALAD, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SAUSAGE_SALAD;
    }
}
