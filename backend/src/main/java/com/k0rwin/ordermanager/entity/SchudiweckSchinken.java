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
@DiscriminatorValue("SCHUDIWECK_SCHINKEN")
public class SchudiweckSchinken extends Meal {

    public SchudiweckSchinken() {
        super(Price.SCHUDIWECK_SCHINKEN);
    }

    public SchudiweckSchinken(Integer amount) {
        super(Price.SCHUDIWECK_SCHINKEN, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SCHUDIWECK_SCHINKEN;
    }
}
