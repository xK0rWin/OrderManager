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
@DiscriminatorValue("KAESEKRUSTI")
public class Kaesekrusti extends Meal {

    public Kaesekrusti() {
        super(Price.KRUSTI_KEASE);
    }

    public Kaesekrusti(Integer amount) {
        super(Price.KRUSTI_KEASE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.KRUSTI_KEASE;
    }
}
