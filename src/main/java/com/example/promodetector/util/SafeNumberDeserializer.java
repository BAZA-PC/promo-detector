package com.example.promodetector.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class SafeNumberDeserializer extends JsonDeserializer<Number> {

    @Override
    public Number deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().trim();

        if (value.isEmpty() || value.equalsIgnoreCase("#NUM!") || value.equalsIgnoreCase("NaN")) {
            return null;
        }

        try {
            if (value.contains(".")) {
                return Double.parseDouble(value.replace(",", "."));
            } else {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            try {
                return new BigDecimal(value.replace(",", "."));
            } catch (Exception ignore) {
                return null;
            }
        }
    }
}