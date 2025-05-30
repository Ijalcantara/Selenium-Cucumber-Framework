package com.cheq.contactlist.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ApiFileUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Loads a JSON file from `src/test/resources/payloads/` and returns it as a Map.
     * 
     * @param fileName The JSON file name (e.g., "createUser.json")
     * @return Map<String, Object> representing JSON key-value pairs
     */
    public static Map<String, Object> loadJsonAsMap(String fileName) {
        try {
            File file = new File("src/main/resources/test_data/" + fileName);
            return objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load or parse JSON file: " + fileName, e);
        }
    }

    /**
     * Optionally, load any arbitrary JSON file from a full path.
     */
    public static Map<String, Object> loadJsonAsMapFromPath(String fullPath) {
        try {
            return objectMapper.readValue(new File(fullPath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON from path: " + fullPath, e);
        }
    }
}
