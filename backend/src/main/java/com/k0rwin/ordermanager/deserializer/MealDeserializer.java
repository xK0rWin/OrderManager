package com.k0rwin.ordermanager.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.k0rwin.ordermanager.entity.Meal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MealDeserializer extends StdDeserializer<Meal> {

    public MealDeserializer() {
        this(null);
    }

    public MealDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Meal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String identifier = node.get("identifier").asText();
        Integer amount = node.get("amount").asInt();

        try {
            Class<?> concreteClass = Class.forName("com.k0rwin.ordermanager.entity." + identifier);
            return (Meal) concreteClass.getDeclaredConstructor(Integer.class)
                    .newInstance(amount);
        }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace(); // Handle exceptions according to your needs
            return null;
        }

    }
}
