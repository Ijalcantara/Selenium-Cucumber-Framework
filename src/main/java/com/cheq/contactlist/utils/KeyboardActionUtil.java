package com.cheq.contactlist.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class KeyboardActionUtil {

	private WebDriver driver;
    private Actions actions;
    private WaitUtil waitUtil;

    public KeyboardActionUtil(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver, timeoutInSeconds);
    }

    public void pressEnter() {
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void pressTab() {
        actions.sendKeys(Keys.TAB).perform();
    }

    public void pressBackspace() {
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }

    /**
     * Types text into the element located by the provided locator with SHIFT key.
     * @param locator Locator of the element
     * @param text Text to type
     */
    public void typeWithShift(By locator, String text) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        actions.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys(text).keyUp(Keys.SHIFT).perform();
    }

    /**
     * Clears the field using keyboard shortcuts (Ctrl+A, Backspace).
     * @param locator Locator of the element
     */
    public void clearFieldUsingKeyboard(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        element.click();
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
    }

    /**
     * Types text into the element located by the provided locator, with an option to clear the field before typing.
     * @param locator Locator of the element
     * @param text Text to type
     * @param clearBeforeTyping If true, clears the field before typing
     */
    public void typeText(By locator, String text, boolean clearBeforeTyping) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        element.click();
        if (clearBeforeTyping) {
            clearFieldUsingKeyboard(locator); // Clearing field before typing
        }
        element.sendKeys(text);
    }

    /**
     * Performs a copy-paste action from one element to another.
     * @param fromElement Source element
     * @param toElement Target element
     */
    public void copyPaste(WebElement fromElement, WebElement toElement) {
        fromElement.click();
        actions.keyDown(Keys.CONTROL).sendKeys("a", "c").keyUp(Keys.CONTROL).perform();
        toElement.click();
        actions.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
    }

    /**
     * Presses the Tab key in the element located by the provided locator.
     * @param locator Locator of the element
     */
    public void pressTabInElement(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        element.click();
        actions.sendKeys(Keys.TAB).perform();
    }

    /**
     * Presses the Enter key in the element located by the provided locator.
     * @param locator Locator of the element
     */
    public void pressEnterInElement(By locator) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        element.click();
        actions.sendKeys(Keys.ENTER).perform();
    }

    /**
     * Types text into the field located by the provided locator and clears the field before typing.
     * @param locator Locator of the element
     * @param text Text to type
     */
    public void typeInField(By locator, String text) {
        WebElement element = waitUtil.waitForElementToBeVisible(locator);
        clearFieldUsingKeyboard(locator); // Clearing field before typing
        element.sendKeys(text);
    }
}
