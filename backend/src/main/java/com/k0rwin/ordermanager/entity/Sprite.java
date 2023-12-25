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
@DiscriminatorValue("SPRITE")
public class Sprite extends Drink {

    public Sprite() {
        super(Price.SPRITE);
    }

    public Sprite(Integer amount) {
        super(Price.SPRITE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPRITE;
    }
}

