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
@DiscriminatorValue("ORANGENSAFT")
public class Orangensaft extends Drink {

    public Orangensaft() {
        super(Price.ORANGENSAFT);
    }

    public Orangensaft(Integer amount) {
        super(Price.ORANGENSAFT, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.ORANGENSAFT;
    }
    @Override
    public String getCategory() {
        return Category.SOFTDRINKS;
    }
}
