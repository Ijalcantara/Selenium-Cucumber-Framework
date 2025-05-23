package com.cheq.contactlist.utils;

import java.io.FileReader;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TestDataUtil {

	private static final String CREDENTIALS_FILE = "src/main/resources/test_data/TestData.json";

	private static Map<String, Map<String, String>> testData;
    /**
     * Gets password for the given email.
     * 
     * @param email User email
     * @param valid true for valid password, false for invalid password
     * @return password string or null if not found
     */
    public static String getPasswordFromJson(String email, boolean valid) {
        try (FileReader reader = new FileReader(CREDENTIALS_FILE)) {
            Map<String, Map<String, String>> credentials = new Gson().fromJson(reader,
                    new TypeToken<Map<String, Map<String, String>>>() {
                    }.getType());

            Map<String, String> userCreds = credentials.get(email);
            if (userCreds == null) {
                AssertionUtil.fail("No credentials found for email: " + email);
                return null;
            }
            return valid ? userCreds.get("valid_password") : userCreds.get("invalid_password");
        } catch (Exception e) {
            e.printStackTrace();
            AssertionUtil.fail("Error reading credentials: " + e.getMessage());
            return null;
        }
    }
    
    public static String getPassword(String email) {
        if (testData.containsKey(email)) {
            return testData.get(email).get("valid_password");
        }
        throw new RuntimeException("No test data found for email: " + email);
    }
}
