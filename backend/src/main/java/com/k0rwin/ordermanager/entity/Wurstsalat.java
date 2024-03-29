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
@DiscriminatorValue("WURSTSALAT")
public class Wurstsalat extends Meal {

    public Wurstsalat() {
        super(Price.WURSTSALAT);
    }

    public Wurstsalat(Integer amount) {
        super(Price.WURSTSALAT, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WURSTSALAT;
    }
}
