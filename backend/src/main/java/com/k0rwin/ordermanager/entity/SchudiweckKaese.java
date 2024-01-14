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
@DiscriminatorValue("SCHUDIWECK_KAESE")
public class SchudiweckKaese extends Meal {

    public SchudiweckKaese() {
        super(Price.SCHUDIWECK_KAESE);
    }

    public SchudiweckKaese(Integer amount) {
        super(Price.SCHUDIWECK_KAESE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SCHUDIWECK_KAESE;
    }
}
