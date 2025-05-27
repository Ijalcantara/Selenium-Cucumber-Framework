package com.cheq.contactlist.utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtil {

	private WebDriver driver;
    private WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;

    /**
     * Constructor that sets implicit and explicit waits.
     *
     * @param driver            WebDriver instance
     * @param timeoutInSeconds  Default timeout for waits
     */
    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT)); // Implicit wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT)); // Explicit wait
    }
    
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted during pause", e);
        }
    }
   
    /**
     * Waits for an element to be visible.
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable.
     */
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    /**
     * Waits for an element to disappear (become invisible).
     */
    public boolean waitForElementToDisappear(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element did not disappear: " + locator, e);
        }
    }

    /**
     * Waits for URL to contain a specific text.
     * @throws InterruptedException 
     */
    public boolean waitForUrlToContain(String partialUrl) {
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            throw new RuntimeException("URL did not contain: " + partialUrl, e);
        }
    }

    /**
     * Waits for specific text to be present in an element.
     */
    public boolean waitForTextToBePresent(By locator, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            throw new RuntimeException("Text '" + text + "' not present in element: " + locator, e);
        }
    }
    
    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
 
    
    public boolean waitForVisibility(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
       }
     }
    

    /**
     * Change implicit wait duration at runtime.
     */
    public void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Change explicit wait duration at runtime.
     */
    public void setExplicitWait(int seconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
    
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
