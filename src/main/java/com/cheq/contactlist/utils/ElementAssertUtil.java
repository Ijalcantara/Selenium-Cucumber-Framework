package com.cheq.contactlist.utils;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    public static void assertElementDisplayed(WebDriver driver, By locator, String elementName) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
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
    
    // Updated assertion 
    public static void assertElementEnabled(WebDriver driver, By locator, String message) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isEnabled(), message);
    }
    
    // email assert
    public static void assertEmailFieldDisplayed(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), "Expected email input field to be displayed");
    }
    
    // password assert
    public static void assertPasswordFieldDisplayed(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), "Expected password input field to be displayed");
    }
    
    //button
    public static void assertSubmitButtonEnabled(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isEnabled(), "Expected submit button to be enabled");
    }
    
    // signup button
    public static void assertSignupButtonEnabled(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isEnabled(), "Expected sign up to be enabled");
    }
    //
    public static void assertErrorMessageContains(WebDriver driver, String expectedText) {
    	WaitUtil waitUtil = new WaitUtil(driver);
        waitUtil.waitForElementToBeVisible(ERROR_MESSAGE);

        String actualMessage = getErrorMessageText(driver);
        Assert.assertTrue(
            actualMessage.contains(expectedText),
            "Expected error message to contain: '" + expectedText + "' but got: '" + actualMessage + "'"
        );
    }
    
    // For error message
    private static final By ERROR_MESSAGE = By.id("error");
    
    public static WebElement getErrorMessageElement(WebDriver driver) {
        return driver.findElement(ERROR_MESSAGE);
    }

    public static By getErrorMessageLocator() {
        return ERROR_MESSAGE;
    }

    public static String getErrorMessageText(WebDriver driver) {
        try {
            WebElement errorElement = getErrorMessageElement(driver);
            return errorElement.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Alert assertAlertIsPresent(WebDriver driver, WaitUtil waitUtil, String expectedTextContains) {
        Alert alert = waitUtil.waitForAlert();
        Assert.assertNotNull(alert, "Alert did not appear");

        String alertText = alert.getText();
        Assert.assertTrue(
            alertText.toLowerCase().contains(expectedTextContains.toLowerCase()),
            "Alert text does not contain expected text: " + expectedTextContains + ". Actual: " + alertText
        );

        return alert;
    }

    public static void acceptAlert(Alert alert) {
        Assert.assertNotNull(alert, "Alert is null, cannot accept");
        alert.accept();
    }
    
    // for deletion
    public static void assertContactIsDeleted(WebDriver driver, String identifier) {
    	List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));

    	boolean contactStillPresent = rows.stream()
    			.anyMatch(row -> row.getText().toLowerCase().contains(identifier.toLowerCase()));

    	ElementAssertUtil.assertFalse(contactStillPresent, "Contact still present but should be deleted: " + identifier);
    }

    public boolean isContactPresentInList(WebDriver driver) {
    	try {
    		List<WebElement> contactRows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
    		return !contactRows.isEmpty();
    	} catch (NoSuchElementException e) {
    		return false;
    	}
    }  

    public static String getFirstContactIdentifier(WebDriver driver) {
    	List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
    	if (!rows.isEmpty()) {
    		return rows.get(0).getText(); 
    	}
    	return "";
    }
   
}
