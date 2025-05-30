package com.cheq.contactlist.utils;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Utility class for wrapping common assertions and validations with enhanced logging.
 * Uses ReporterUtil for logging results and capturing errors gracefully.
 */
public class ElementAssertUtil {
	
	// Generic text equality assertion with logging
    public static void assertTextEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
            throw e;
        }
    }

    // Assert condition true with logging
    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
            throw e;
        }
    }

    // Assert condition false with logging
    public static void assertFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
            throw e;
        }
    }

    // Assert element located by locator is displayed
    public static void assertElementDisplayed(WebDriver driver, By locator, String elementName) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
            ReporterUtil.logPass("Element displayed as expected: " + elementName);
        } catch (Exception e) {
            ReporterUtil.logFail("Element not displayed or exception occurred: " + elementName + " | " + e.getMessage());
            throw e;
        }
    }

    // Assert given WebElement is displayed
    public static void assertElementDisplayed(WebElement element, String elementName) {
        try {
            Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
            ReporterUtil.logPass("Element displayed as expected: " + elementName);
        } catch (Exception e) {
            ReporterUtil.logFail("Element not displayed or exception occurred: " + elementName + " | " + e.getMessage());
            throw e;
        }
    }

    // Assert object not null
    public static void assertNotNull(Object object, String message) {
        try {
            Assert.assertNotNull(object, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
            throw e;
        }
    }

    // Force test failure with message
    public static void fail(String message) {
        ReporterUtil.logFail(message);
        Assert.fail(message);
    }

    // Assert element located by locator is enabled
    public static void assertElementEnabled(WebDriver driver, By locator, String message) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isEnabled(), message);
            ReporterUtil.logPass(message);
        } catch (Exception e) {
            ReporterUtil.logFail(message + " | Exception: " + e.getMessage());
            throw e;
        }
    }

    // Assert email field displayed
    public static void assertEmailFieldDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), "Expected email input field to be displayed");
            ReporterUtil.logPass("Email input field displayed");
        } catch (Exception e) {
            ReporterUtil.logFail("Email input field not displayed or exception: " + e.getMessage());
            throw e;
        }
    }

    // Assert password field displayed
    public static void assertPasswordFieldDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isDisplayed(), "Expected password input field to be displayed");
            ReporterUtil.logPass("Password input field displayed");
        } catch (Exception e) {
            ReporterUtil.logFail("Password input field not displayed or exception: " + e.getMessage());
            throw e;
        }
    }

    // Assert submit button enabled
    public static void assertSubmitButtonEnabled(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isEnabled(), "Expected submit button to be enabled");
            ReporterUtil.logPass("Submit button enabled");
        } catch (Exception e) {
            ReporterUtil.logFail("Submit button not enabled or exception: " + e.getMessage());
            throw e;
        }
    }

    // Assert signup button enabled
    public static void assertSignupButtonEnabled(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            Assert.assertTrue(element.isEnabled(), "Expected sign up to be enabled");
            ReporterUtil.logPass("Signup button enabled");
        } catch (Exception e) {
            ReporterUtil.logFail("Signup button not enabled or exception: " + e.getMessage());
            throw e;
        }
    }

    // Assert error message contains expected text
    public static void assertErrorMessageContains(WebDriver driver, String expectedText) throws Exception {
        try {
            WaitUtil waitUtil = new WaitUtil(driver);
            waitUtil.waitForElementToBeVisible(ERROR_MESSAGE);

            String actualMessage = getErrorMessageText(driver);
            Assert.assertTrue(
                actualMessage != null && actualMessage.contains(expectedText),
                "Expected error message to contain: '" + expectedText + "' but got: '" + actualMessage + "'"
            );
            ReporterUtil.logPass("Error message contains expected text: " + expectedText);
        } catch (Exception e) {
            ReporterUtil.logFail("Error message did not contain expected text or exception: " + e.getMessage());
            throw e;
        }
    }

    private static final By ERROR_MESSAGE = By.id("error");

    // Get error message WebElement
    public static WebElement getErrorMessageElement(WebDriver driver) {
        try {
            return driver.findElement(ERROR_MESSAGE);
        } catch (Exception e) {
            ReporterUtil.logError("Error message element not found: " + e.getMessage());
            throw e;
        }
    }

    // Get error message locator
    public static By getErrorMessageLocator() {
        return ERROR_MESSAGE;
    }

    // Get error message text
    public static String getErrorMessageText(WebDriver driver) {
        try {
            WebElement errorElement = getErrorMessageElement(driver);
            return errorElement.getText().trim();
        } catch (Exception e) {
            ReporterUtil.logError("Failed to get error message text: " + e.getMessage());
            return null;
        }
    }

    // Assert alert is present with expected text and return alert
    public static Alert assertAlertIsPresent(WebDriver driver, WaitUtil waitUtil, String expectedTextContains) throws Exception {
        try {
            Alert alert = waitUtil.waitForAlert();
            Assert.assertNotNull(alert, "Alert did not appear");

            String alertText = alert.getText();
            Assert.assertTrue(
                alertText.toLowerCase().contains(expectedTextContains.toLowerCase()),
                "Alert text does not contain expected text: " + expectedTextContains + ". Actual: " + alertText
            );
            ReporterUtil.logPass("Alert appeared with expected text: " + expectedTextContains);
            return alert;
        } catch (Exception e) {
            ReporterUtil.logFail("Alert assertion failed or exception: " + e.getMessage());
            throw e;
        }
    }

    // Accept alert safely
    public static void acceptAlert(Alert alert) {
        try {
            Assert.assertNotNull(alert, "Alert is null, cannot accept");
            alert.accept();
            ReporterUtil.logPass("Alert accepted");
        } catch (Exception e) {
            ReporterUtil.logFail("Failed to accept alert: " + e.getMessage());
            throw e;
        }
    }
    
    // Confirm deletion: either alert or modal confirm button
    public static void confirmDeletion(WebDriver driver, WaitUtil waitUtil) throws IOException, AWTException {
        Alert alert = waitUtil.waitForAlertOrNull(5);

        if (alert != null) {
            acceptAlert(alert);
        } else {
            // Replace this locator with your modal confirm button locator
            By modalConfirmButton = By.id("delete");

            WebElement confirmBtn = waitUtil.waitForElementToBeClickable(modalConfirmButton);
            confirmBtn.click();

            // Optional short pause to allow UI update
            waitUtil.pause(1);
        }
    }

    // Assert contact is deleted by checking rows no longer contain identifier text
    public static void assertContactIsDeleted(WebDriver driver, String identifier) {
        try {
            List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));

            boolean contactStillPresent = rows.stream()
                    .anyMatch(row -> row.getText().toLowerCase().contains(identifier.toLowerCase()));

            assertFalse(contactStillPresent, "Contact still present but should be deleted: " + identifier);
            ReporterUtil.logPass("Contact deleted as expected: " + identifier);
        } catch (Exception e) {
            ReporterUtil.logFail("Contact deletion assertion failed or exception: " + e.getMessage());
            throw e;
        }
    }

    // API assertions
    public static void assertStatusCode(int actual, int expected) {
        try {
            Assert.assertEquals(actual, expected, "Unexpected HTTP status code");
            ReporterUtil.logPass("Status code matched: " + expected);
        } catch (AssertionError e) {
            ReporterUtil.logFail("Unexpected HTTP status code. Expected: " + expected + ", Actual: " + actual);
            throw e;
        }
    }

    public static void assertNotEmptyList(List<?> list, String message) {
        try {
            Assert.assertFalse(list == null || list.isEmpty(), message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | List is empty or null");
            throw e;
        }
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
            throw e;
        }
    }

    public static void assertNotNullApi(Object obj, String message) {
        try {
            Assert.assertNotNull(obj, message);
            ReporterUtil.logPass(message);
        } catch (AssertionError e) {
            ReporterUtil.logFail(message + " | Object is null");
            throw e;
        }
    }

    // Check if any contacts are present in the list
    public boolean isContactPresentInList(WebDriver driver) {
        try {
            List<WebElement> contactRows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
            return !contactRows.isEmpty();
        } catch (NoSuchElementException e) {
            ReporterUtil.logWarn("Contact rows not found: " + e.getMessage());
            return false;
        }
    }

    // Get the text of the first contact in the list
    public static String getFirstContactIdentifier(WebDriver driver) {
        try {
            List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
            if (!rows.isEmpty()) {
                return rows.get(0).getText();
            }
            return "";
        } catch (Exception e) {
            ReporterUtil.logError("Failed to get first contact identifier: " + e.getMessage());
            return "";
        }
    }
	
//	public static void assertTextEquals(String actual, String expected, String message) {
//        try {
//            Assert.assertEquals(actual, expected, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertTrue(boolean condition, String message) {
//        try {
//            Assert.assertTrue(condition, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertFalse(boolean condition, String message) {
//        try {
//            Assert.assertFalse(condition, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertElementDisplayed(WebDriver driver, By locator, String elementName) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
//            ReporterUtil.logPass("Element displayed as expected: " + elementName);
//        } catch (Exception e) {
//            ReporterUtil.logFail("Element not displayed or exception occurred: " + elementName + " | " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertElementDisplayed(WebElement element, String elementName) {
//        try {
//            Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
//            ReporterUtil.logPass("Element displayed as expected: " + elementName);
//        } catch (Exception e) {
//            ReporterUtil.logFail("Element not displayed or exception occurred: " + elementName + " | " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertNotNull(Object object, String message) {
//        try {
//            Assert.assertNotNull(object, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void fail(String message) {
//        ReporterUtil.logFail(message);
//        Assert.fail(message);
//    }
//
//    public static void assertElementEnabled(WebDriver driver, By locator, String message) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isEnabled(), message);
//            ReporterUtil.logPass(message);
//        } catch (Exception e) {
//            ReporterUtil.logFail(message + " | Exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertEmailFieldDisplayed(WebDriver driver, By locator) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isDisplayed(), "Expected email input field to be displayed");
//            ReporterUtil.logPass("Email input field displayed");
//        } catch (Exception e) {
//            ReporterUtil.logFail("Email input field not displayed or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertPasswordFieldDisplayed(WebDriver driver, By locator) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isDisplayed(), "Expected password input field to be displayed");
//            ReporterUtil.logPass("Password input field displayed");
//        } catch (Exception e) {
//            ReporterUtil.logFail("Password input field not displayed or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertSubmitButtonEnabled(WebDriver driver, By locator) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isEnabled(), "Expected submit button to be enabled");
//            ReporterUtil.logPass("Submit button enabled");
//        } catch (Exception e) {
//            ReporterUtil.logFail("Submit button not enabled or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertSignupButtonEnabled(WebDriver driver, By locator) {
//        try {
//            WebElement element = driver.findElement(locator);
//            Assert.assertTrue(element.isEnabled(), "Expected sign up to be enabled");
//            ReporterUtil.logPass("Signup button enabled");
//        } catch (Exception e) {
//            ReporterUtil.logFail("Signup button not enabled or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertErrorMessageContains(WebDriver driver, String expectedText) {
//        try {
//            WaitUtil waitUtil = new WaitUtil(driver);
//            waitUtil.waitForElementToBeVisible(ERROR_MESSAGE);
//
//            String actualMessage = getErrorMessageText(driver);
//            Assert.assertTrue(
//                actualMessage != null && actualMessage.contains(expectedText),
//                "Expected error message to contain: '" + expectedText + "' but got: '" + actualMessage + "'"
//            );
//            ReporterUtil.logPass("Error message contains expected text: " + expectedText);
//        } catch (Exception e) {
//            ReporterUtil.logFail("Error message did not contain expected text or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    private static final By ERROR_MESSAGE = By.id("error");
//
//    public static WebElement getErrorMessageElement(WebDriver driver) {
//        try {
//            return driver.findElement(ERROR_MESSAGE);
//        } catch (Exception e) {
//            ReporterUtil.logError("Error message element not found: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static By getErrorMessageLocator() {
//        return ERROR_MESSAGE;
//    }
//
//    public static String getErrorMessageText(WebDriver driver) {
//        try {
//            WebElement errorElement = getErrorMessageElement(driver);
//            return errorElement.getText().trim();
//        } catch (Exception e) {
//            ReporterUtil.logError("Failed to get error message text: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public static Alert assertAlertIsPresent(WebDriver driver, WaitUtil waitUtil, String expectedTextContains) {
//        try {
//            Alert alert = waitUtil.waitForAlert();
//            Assert.assertNotNull(alert, "Alert did not appear");
//
//            String alertText = alert.getText();
//            Assert.assertTrue(
//                alertText.toLowerCase().contains(expectedTextContains.toLowerCase()),
//                "Alert text does not contain expected text: " + expectedTextContains + ". Actual: " + alertText
//            );
//            ReporterUtil.logPass("Alert appeared with expected text: " + expectedTextContains);
//            return alert;
//        } catch (Exception e) {
//            ReporterUtil.logFail("Alert assertion failed or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void acceptAlert(Alert alert) {
//        try {
//            Assert.assertNotNull(alert, "Alert is null, cannot accept");
//            alert.accept();
//            ReporterUtil.logPass("Alert accepted");
//        } catch (Exception e) {
//            ReporterUtil.logFail("Failed to accept alert: " + e.getMessage());
//            throw e;
//        }
//    }
//    
//    public static void confirmDeletion(WebDriver driver, WaitUtil waitUtil) {
//        Alert alert = waitUtil.waitForAlertOrNull(5);
//
//        if (alert != null) {
//            acceptAlert(alert);
//        } else {
//            // Replace this locator with your modal confirm button
//            By modalConfirmButton = By.id("delete");
//
//            WebElement confirmBtn = waitUtil.waitForElementToBeClickable(modalConfirmButton);
//            confirmBtn.click();
//
//            // Optional short pause to allow UI update
//            waitUtil.pause(1);
//        }
//    }
//    
//
//    
//
//
//    public static void assertContactIsDeleted(WebDriver driver, String identifier) {
//        try {
//            List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
//
//            boolean contactStillPresent = rows.stream()
//                    .anyMatch(row -> row.getText().toLowerCase().contains(identifier.toLowerCase()));
//
//            assertFalse(contactStillPresent, "Contact still present but should be deleted: " + identifier);
//            ReporterUtil.logPass("Contact deleted as expected: " + identifier);
//        } catch (Exception e) {
//            ReporterUtil.logFail("Contact deletion assertion failed or exception: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    // Assertions for API calls
//
//    public static void assertStatusCode(int actual, int expected) {
//        try {
//            Assert.assertEquals(actual, expected, "Unexpected HTTP status code");
//            ReporterUtil.logPass("Status code matched: " + expected);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail("Unexpected HTTP status code. Expected: " + expected + ", Actual: " + actual);
//            throw e;
//        }
//    }
//
//    public static void assertNotEmptyList(List<?> list, String message) {
//        try {
//            Assert.assertFalse(list == null || list.isEmpty(), message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | List is empty or null");
//            throw e;
//        }
//    }
//
//    public static void assertEquals(Object actual, Object expected, String message) {
//        try {
//            Assert.assertEquals(actual, expected, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Assertion failed: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void assertNotNullApi(Object obj, String message) {
//        try {
//            Assert.assertNotNull(obj, message);
//            ReporterUtil.logPass(message);
//        } catch (AssertionError e) {
//            ReporterUtil.logFail(message + " | Object is null");
//            throw e;
//        }
//    }
//
//    public boolean isContactPresentInList(WebDriver driver) {
//        try {
//            List<WebElement> contactRows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
//            return !contactRows.isEmpty();
//        } catch (NoSuchElementException e) {
//            ReporterUtil.logWarn("Contact rows not found: " + e.getMessage());
//            return false;
//        }
//    }
//
//    public static String getFirstContactIdentifier(WebDriver driver) {
//        try {
//            List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
//            if (!rows.isEmpty()) {
//                return rows.get(0).getText();
//            }
//            return "";
//        } catch (Exception e) {
//            ReporterUtil.logError("Failed to get first contact identifier: " + e.getMessage());
//            return "";
//        }
//    }
//	
////	public static void assertTextEquals(String actual, String expected, String message) {
////        Assert.assertEquals(actual, expected, message);
////    }
////
////    public static void assertTrue(boolean condition, String message) {
////        Assert.assertTrue(condition, message);
////    }
////
////    public static void assertFalse(boolean condition, String message) {
////        Assert.assertFalse(condition, message);
////    }
////
////    public static void assertElementDisplayed(WebDriver driver, By locator, String elementName) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
////    }
////
////    public static void assertElementDisplayed(WebElement element, String elementName) {
////        Assert.assertTrue(element.isDisplayed(), "Expected element to be displayed: " + elementName);
////    }
////
////    public static void assertNotNull(Object object, String message) {
////        Assert.assertNotNull(object, message);
////    }
////
////    public static void fail(String message) {
////        Assert.fail(message);
////    }
////    
////    // Updated assertion 
////    public static void assertElementEnabled(WebDriver driver, By locator, String message) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isEnabled(), message);
////    }
////    
////    // email assert
////    public static void assertEmailFieldDisplayed(WebDriver driver, By locator) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isDisplayed(), "Expected email input field to be displayed");
////    }
////    
////    // password assert
////    public static void assertPasswordFieldDisplayed(WebDriver driver, By locator) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isDisplayed(), "Expected password input field to be displayed");
////    }
////    
////    //button
////    public static void assertSubmitButtonEnabled(WebDriver driver, By locator) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isEnabled(), "Expected submit button to be enabled");
////    }
////    
////    // signup button
////    public static void assertSignupButtonEnabled(WebDriver driver, By locator) {
////        WebElement element = driver.findElement(locator);
////        Assert.assertTrue(element.isEnabled(), "Expected sign up to be enabled");
////    }
////    //
////    public static void assertErrorMessageContains(WebDriver driver, String expectedText) {
////    	WaitUtil waitUtil = new WaitUtil(driver);
////        waitUtil.waitForElementToBeVisible(ERROR_MESSAGE);
////
////        String actualMessage = getErrorMessageText(driver);
////        Assert.assertTrue(
////            actualMessage.contains(expectedText),
////            "Expected error message to contain: '" + expectedText + "' but got: '" + actualMessage + "'"
////        );
////    }
////    
////    // For error message
////    private static final By ERROR_MESSAGE = By.id("error");
////    
////    public static WebElement getErrorMessageElement(WebDriver driver) {
////        return driver.findElement(ERROR_MESSAGE);
////    }
////
////    public static By getErrorMessageLocator() {
////        return ERROR_MESSAGE;
////    }
////
////    public static String getErrorMessageText(WebDriver driver) {
////        try {
////            WebElement errorElement = getErrorMessageElement(driver);
////            return errorElement.getText().trim();
////        } catch (Exception e) {
////            return null;
////        }
////    }
////    
////    public static Alert assertAlertIsPresent(WebDriver driver, WaitUtil waitUtil, String expectedTextContains) {
////        Alert alert = waitUtil.waitForAlert();
////        Assert.assertNotNull(alert, "Alert did not appear");
////
////        String alertText = alert.getText();
////        Assert.assertTrue(
////            alertText.toLowerCase().contains(expectedTextContains.toLowerCase()),
////            "Alert text does not contain expected text: " + expectedTextContains + ". Actual: " + alertText
////        );
////
////        return alert;
////    }
////
////    public static void acceptAlert(Alert alert) {
////        Assert.assertNotNull(alert, "Alert is null, cannot accept");
////        alert.accept();
////    }
////    
////    // for deletion
////    public static void assertContactIsDeleted(WebDriver driver, String identifier) {
////    	List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
////
////    	boolean contactStillPresent = rows.stream()
////    			.anyMatch(row -> row.getText().toLowerCase().contains(identifier.toLowerCase()));
////
////    	ElementAssertUtil.assertFalse(contactStillPresent, "Contact still present but should be deleted: " + identifier);
////    }
////
////    
////    // Assertions for API calls
////    
////    public static void assertStatusCode(int actual, int expected) {
////        Assert.assertEquals(actual, expected, "Unexpected HTTP status code");
////    }
////
////    public static void assertNotEmptyList(List<?> list, String message) {
////        Assert.assertFalse(list == null || list.isEmpty(), message);
////    }
////
////    public static void assertEquals(Object actual, Object expected, String message) {
////        Assert.assertEquals(actual, expected, message);
////    }
////
////    public static void assertNotNullApi(Object obj, String message) {
////        Assert.assertNotNull(obj, message);
////    }
////    
////    
////    public boolean isContactPresentInList(WebDriver driver) {
////    	try {
////    		List<WebElement> contactRows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
////    		return !contactRows.isEmpty();
////    	} catch (NoSuchElementException e) {
////    		return false;
////    	}
////    }  
////
////    public static String getFirstContactIdentifier(WebDriver driver) {
////    	List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
////    	if (!rows.isEmpty()) {
////    		return rows.get(0).getText(); 
////    	}
////    	return "";
////    }
   
}
