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
@DiscriminatorValue("JOHANNISBEERSCHORLE")
public class JohannisbeerSchorle extends Drink {

    public JohannisbeerSchorle() {
        super(Price.JOHANNISBEERSCHORLE);
    }

    public JohannisbeerSchorle(Integer amount) {
        super(Price.JOHANNISBEERSCHORLE, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.JOHANNISBEERSCHORLE;
    }
    @Override
    public String getCategory() {
        return Category.SOFTDRINKS;
    }
}
