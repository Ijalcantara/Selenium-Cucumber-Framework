package com.cheq.contactlist.utils;

import java.io.IOException;

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
    private ReporterUtil reporterUtil;
    private int timeout;

    public KeyboardActionUtil(WebDriver driver, ScreenshotUtil screenshotUtil) throws IOException {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver);
        this.timeout = 10;
        this.reporterUtil = new ReporterUtil(driver, screenshotUtil);

        try {
            reporterUtil.resultsReporter(null, LogLevel.INFO,
                LogMessageUtil.initialized("KeyboardActionUtil", timeout),
                "initialize");
        } catch (Exception e) {
            e.printStackTrace(); // optional
        }
    }

    public void pressEnter() {
        try {
            actions.sendKeys(Keys.ENTER).perform();
            reporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("ENTER", "keyboard"), "press ENTER");
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("ENTER", "keyboard"), "press ENTER");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pressTab() {
        try {
            actions.sendKeys(Keys.TAB).perform();
            reporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("TAB", "keyboard"), "press TAB");
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("TAB", "keyboard"), "press TAB");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pressBackspace() {
        try {
            actions.sendKeys(Keys.BACK_SPACE).perform();
            reporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.pressKeySuccess("BACKSPACE", "keyboard"), "press BACKSPACE");
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("BACKSPACE", "keyboard"), "press BACKSPACE");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void typeWithShift(By locator, String text) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys(text).keyUp(Keys.SHIFT).perform();
            reporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.actionSuccess("type with SHIFT", locator), locator.toString());
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.actionFail("type with SHIFT", locator), locator.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void clearFieldUsingKeyboard(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
            reporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.clearFieldSuccess(locator), locator.toString());
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.clearFieldFailed(locator), locator.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
            reporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.typeTextSuccess(text, locator), locator.toString());
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.typeTextFailed(text, locator), locator.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pressTabInElement(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.sendKeys(Keys.TAB).perform();
            reporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.pressKeySuccess("TAB", locator), locator.toString());
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("TAB", locator), locator.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pressEnterInElement(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            element.click();
            actions.sendKeys(Keys.ENTER).perform();
            reporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.pressKeySuccess("ENTER", locator), locator.toString());
        } catch (Exception e) {
            try {
                reporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.pressKeyFailed("ENTER", locator), locator.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}