package com.cheq.contactlist.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseActionUtil {

	
	private WebDriver driver;
    private Actions actions;
    private WaitUtil waitUtil;

    public MouseActionUtil(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver, 10); // Default 10 seconds, or you can pass it as a param
    }

    public void hover(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        actions.moveToElement(element).perform();
    }

    public void hover(WebElement element) {
        waitUtil.waitForElementToBeVisible(getByFromElement(element)); // Optional validation
        actions.moveToElement(element).perform();
    }

    public void rightClick(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        actions.contextClick(element).perform();
    }

    public void doubleClick(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        actions.doubleClick(element).perform();
    }

    public void clickAndHold(By locator) {
        WebElement element = waitUtil.waitForElementToBeClickable(locator);
        actions.clickAndHold(element).perform();
    }

    public void releaseClick(By locator) {
        WebElement element = waitUtil.waitForElementToBeClickable(locator);
        actions.release(element).perform();
    }

    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = waitUtil.waitForElementToBeVisible(sourceLocator);
        WebElement target = waitUtil.waitForElementToBeVisible(targetLocator);
        actions.dragAndDrop(source, target).perform();
    }

    public void click(By locator) {
        WebElement element = waitUtil.waitForElementToBeClickable(locator);
        actions.moveToElement(element).click().perform();
    }

    public void click(WebElement element) {
        waitUtil.waitForElementToBeVisible(getByFromElement(element)); // Optional validation
        actions.moveToElement(element).click().perform();
    }

    /**
     * Helper method: attempts to build a By locator from a WebElement (optional).
     * If you have full control over your test framework, consider passing By instead of WebElement.
     */
    private By getByFromElement(WebElement element) {
        // Dummy fallback, can’t really extract locator from WebElement in Selenium directly.
        // Only usable for validation; override this method if needed.
        return By.xpath("."); // Always returns a dummy; real-world use would pass a By instead.
    }
}
