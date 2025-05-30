package com.cheq.contactlist.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtil {

    private static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Send a POST request with JSON body
     */
    public static Response post(String endpoint, Map<String, Object> body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Send a GET request
     */
    public static Response get(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Send a PUT request with JSON body
     */
    public static Response put(String endpoint, Map<String, Object> body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    /**
     * Send a DELETE request
     */
    public static Response delete(String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
    
    /**
     * Fail test execution when response status does not match the expected one.
     */
    public static boolean failStatusMismatch(int expected, int actual, String responseBody) {
        throw new RuntimeException("Expected status code " + expected + " but got " + actual + ". Response: " + responseBody);
    }
}
