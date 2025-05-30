package com.cheq.contactlist.utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FakerUtil {

    private static final Faker faker = new Faker();

    // Basic Fields
    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getUniqueEmail() {
        return "user_" + System.currentTimeMillis() + "@testmail.com";
    }

    public static String getPassword() {
        return faker.internet().password(8, 16, true, true);
    }

    // Address Fields
    public static String getStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String getSecondaryAddress() {
        return faker.address().secondaryAddress();
    }

    public static String getCity() {
        return faker.address().city();
    }

    public static String getPostalCode() {
        return faker.address().zipCode();
    }

    public static String getPhoneNumber() {
        return faker.number().digits(7);
    }

    public static String getCountry() {
        return faker.address().country();
    }

    public static String getState() {
        String[] states = {
            "California", "Texas", "Florida", "New York", "Illinois",
            "Pennsylvania", "Ohio", "Georgia", "North Carolina", "Michigan"
        };
        return states[faker.random().nextInt(states.length)];
    }

    public static String getDateOfBirth() {
        Date birthDate = faker.date().birthday();
        return new SimpleDateFormat("yyyy/MM/dd").format(birthDate);
    }

    // UUID Generator
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}