package com.example.promodetector.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonLoader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> loadList(String path, TypeReference<List<T>> type) {
        try (InputStream is = JsonLoader.class.getResourceAsStream(path)) {
            return mapper.readValue(is, type);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON from: " + path, e);
        }
    }
}