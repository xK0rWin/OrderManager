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
@DiscriminatorValue("WASSER_CLASSIC")
public class WasserClassic extends Drink {

    public WasserClassic() {
        super(Price.WASSER_CLASSIC);
    }

    public WasserClassic(Integer amount) {
        super(Price.WASSER_CLASSIC, amount);
    }

    @Override
    public String getIdentifier() {
        return Identifier.WASSER_CLASSIC;
    }
}
