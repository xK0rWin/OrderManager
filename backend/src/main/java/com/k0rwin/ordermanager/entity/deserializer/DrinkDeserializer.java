package com.k0rwin.ordermanager.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.k0rwin.ordermanager.bundle.Identifier;
import com.k0rwin.ordermanager.entity.Coke;
import com.k0rwin.ordermanager.entity.Drink;
import com.k0rwin.ordermanager.entity.Fanta;
import com.k0rwin.ordermanager.entity.Meal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DrinkDeserializer extends StdDeserializer<Drink> {

    public DrinkDeserializer() {
        this(null);
    }

    public DrinkDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Drink deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String identifier = node.get("identifier").asText();
        Integer amount = node.get("amount").asInt();

        try {
            Class<?> concreteClass = Class.forName("com.k0rwin.ordermanager.entity." + identifier);
            return (Drink) concreteClass.getDeclaredConstructor(Integer.class)
                    .newInstance(amount);
        }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace(); // Handle exceptions according to your needs
            return null;
        }
    }
}