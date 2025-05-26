package com.cheq.contactlist.utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestDataUtil {

	 private static final String CREDENTIALS_FILE = "src/main/resources/test_data/TestData.json";

	    private static Map<String, Map<String, String>> testData;

	    // Loads test data only once
	    private static void loadTestData() {
	        if (testData == null) {
	            try (FileReader reader = new FileReader(CREDENTIALS_FILE)) {
	                Type type = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
	                testData = new Gson().fromJson(reader, type);
	            } catch (IOException e) {
	                throw new RuntimeException("Failed to load test data from file: " + CREDENTIALS_FILE, e);
	            }
	        }
	    }

	    public static String getPasswordFromJson(String email, boolean valid) {
	        loadTestData();
	        Map<String, String> creds = testData.get(email);
	        if (creds == null) {
	            throw new RuntimeException("No credentials found for email: " + email);
	        }
	        return valid ? creds.get("valid_password") : creds.get("invalid_password");
	    }

	    public static String getPassword(String email) {
	        loadTestData();
	        if (testData.containsKey(email)) {
	            return testData.get(email).get("valid_password");
	        }
	        throw new RuntimeException("No test data found for email: " + email);
	    }
}
