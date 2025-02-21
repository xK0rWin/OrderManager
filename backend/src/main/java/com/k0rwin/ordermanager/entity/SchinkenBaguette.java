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
@DiscriminatorValue("SCHINKEN_BAGUETTE")
public class SchinkenBaguette extends Meal {
    public SchinkenBaguette() {
        super(Price.SCHINKEN_BAGUETTE);
    }
    public SchinkenBaguette(Integer amount) {
        super(Price.SCHINKEN_BAGUETTE, amount);
    }
    @Override
    public String getIdentifier() {
        return Identifier.SCHINKEN_BAGUETTE;
    }
}