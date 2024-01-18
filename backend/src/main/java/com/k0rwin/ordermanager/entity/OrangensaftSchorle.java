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
@DiscriminatorValue("ORANGENSAFTSCHORLE")
public class OrangensaftSchorle extends Drink {

    public OrangensaftSchorle() {
        super(Price.ORANGENSAFTSCHORLE);
    }

    public OrangensaftSchorle(Integer amount) {
        super(Price.ORANGENSAFTSCHORLE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.ORANGENSAFTSCHORLE;
    }
    @Override
    public String getCategory() {
        return Category.SOFTDRINKS;
    }
}
