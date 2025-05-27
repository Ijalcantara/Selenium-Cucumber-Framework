package com.cheq.contactlist.utils;

public class TestDataResolver {

	public static String resolvePassword(String email, String passwordPlaceholder, boolean isValid) {
        switch (passwordPlaceholder) {
            case "{password}":
                return TestDataUtil.getPasswordFromJson(email, isValid);
            case "{empty}":
                return "";
            default:
                return passwordPlaceholder;
        }
    }
}
