package com.cheq.contactlist.utils;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResponseUtil {

	/**
     * Saves the API response to a file in /src/test/resources/responses
     *
     * @param response The RestAssured response to save
     * @param fileName The file name to save as (e.g., "loginResponse.json")
     */
    public static void saveResponseToFile(Response response, String fileName) {
        String directoryPath = "src/test/resources/responses";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        File outputFile = new File(directory, fileName);
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(response.asPrettyString());
            System.out.println("✅ Response saved to: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Failed to save response: " + e.getMessage());
        }
    }
}
