package com.cheq.contactlist.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Utility class for performing keyboard actions using Selenium WebDriver.
 * Integrates with {@link WaitUtil} for synchronization and {@link ReporterUtil} for logging.
 */
public class KeyboardActionUtil {

    private WebDriver driver;
    private Actions actions;
    private WaitUtil waitUtil;
    private int timeout;

    public KeyboardActionUtil(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver);
        this.timeout = 10;
        ReporterUtil.resultsReporter(null, LogLevel.INFO,
            LogMessageUtil.initialized("KeyboardActionUtil", timeout),
            "initialize",
            "KeyboardActionUtil"
        );
    }

    public void pressEnter() {
        try {
            actions.sendKeys(Keys.ENTER).perform();
            ReporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("ENTER", "keyboard"), "press ENTER", "keyboard");
        } catch (Exception e) {
            ReporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("ENTER", "keyboard"), "press ENTER", "keyboard");
        }
    }

    public void pressTab() {
        try {
            actions.sendKeys(Keys.TAB).perform();
            ReporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("TAB", "keyboard"), "press TAB", "keyboard");
        } catch (Exception e) {
            ReporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("TAB", "keyboard"), "press TAB", "keyboard");
        }
    }

    public void pressBackspace() {
        try {
            actions.sendKeys(Keys.BACK_SPACE).perform();
            ReporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("BACKSPACE", "keyboard"), "press BACKSPACE", "keyboard");
        } catch (Exception e) {
            ReporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("BACKSPACE", "keyboard"), "press BACKSPACE", "keyboard");
        }
    }

    public void typeWithShift(By locator, String text) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys(text).keyUp(Keys.SHIFT).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.actionSuccess("type with SHIFT", locator), "type with SHIFT", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.actionFail("type with SHIFT", locator), "type with SHIFT", locator.toString());
        }
    }

    public void clearFieldUsingKeyboard(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.clearFieldSuccess(locator), "clear field using keyboard", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.clearFieldFailed(locator), "clear field using keyboard", locator.toString());
        }
    }

    public void typeText(By locator, String text, boolean clearBeforeTyping) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            if (clearBeforeTyping) {
                clearFieldUsingKeyboard(locator);
            }
            for (char ch : text.toCharArray()) {
                element.sendKeys(Character.toString(ch));
                waitUtil.sleep(100);
            }
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.typeTextSuccess(text, locator) + " | Text: " + text, "type text", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.typeTextFailed(text, locator), "type text", locator.toString());
        }
    }


    public void pressTabInElement(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.sendKeys(Keys.TAB).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.pressKeySuccess("TAB", locator), "press TAB", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("TAB", locator), "press TAB", locator.toString());
        }
    }

    public void pressEnterInElement(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.sendKeys(Keys.ENTER).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.pressKeySuccess("ENTER", locator), "press ENTER", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("ENTER", locator), "press ENTER", locator.toString());
        }
    }
}