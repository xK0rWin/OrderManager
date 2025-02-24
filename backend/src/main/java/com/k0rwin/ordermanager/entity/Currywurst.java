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
@DiscriminatorValue("CURRYWURST")
public class Currywurst extends Meal {

    public Currywurst() {
        super(Price.CURRYWURST);
    }

    public Currywurst(Integer amount) {
        super(Price.CURRYWURST, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.CURRYWURST;
    }
}
