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
@DiscriminatorValue("SPAETB_KA_MILD_FL")
public class SpaetburgunderHexMildFl extends Drink {

    public SpaetburgunderHexMildFl() {
        super(Price.SPAETB_KA_MILD_FL);
    }

    public SpaetburgunderHexMildFl(Integer amount) {
        super(Price.SPAETB_KA_MILD_FL, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.SPAETB_KA_MILD_FL;
    }
}
