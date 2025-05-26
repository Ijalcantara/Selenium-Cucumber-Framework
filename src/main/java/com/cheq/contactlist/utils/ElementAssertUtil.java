package com.cheq.contactlist.utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ElementAssertUtil {

	public static void assertTextEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public static void assertElementDisplayed(boolean isDisplayed, String elementName) {
        Assert.assertTrue(isDisplayed, "Expected element to be displayed: " + elementName);
    }

    public static void assertElementDisplayed(WebElement element, String elementName) {
        Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
    }

    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void fail(String message) {
        Assert.fail(message);
    }
}
