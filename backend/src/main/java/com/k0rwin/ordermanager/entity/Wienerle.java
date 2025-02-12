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
@DiscriminatorValue("WIENERLE")
public class Wienerle  extends Meal {
    public Wienerle() {
        super(Price.WIENERLE);
    }
    public Wienerle(Integer amount) {
        super(Price.WIENERLE, amount);
    }
    @Override
    public String getIdentifier() {
        return Identifier.WIENERLE;
    }
}