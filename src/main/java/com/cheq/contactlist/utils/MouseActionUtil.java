package com.cheq.contactlist.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Utility class for performing mouse-related actions like hover, click, and right-click
 * using Selenium's {@link Actions} class.
 * All actions are wrapped with logging using {@link ReporterUtil} and wait conditions using {@link WaitUtil}.
 */
public class MouseActionUtil {

    private WebDriver driver;
    private Actions actions;
    private WaitUtil waitUtil;

    /**
     * Constructor to initialize mouse actions and utilities.
     *
     * @param driver The WebDriver instance to be used for performing actions
     */
    public MouseActionUtil(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver);
        ReporterUtil.resultsReporter(
                null,
                LogLevel.INFO,
                LogMessageUtil.CLASS_INITIALIZED_MESSAGE,
                "MouseActionUtil",
                "10"
        );
    }

    public void hover(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.moveToElement(element).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.ACTION_SUCCESS_MESSAGE, "hover", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.ACTION_FAILED_MESSAGE, "hover", locator.toString());
        }
    }

    public void rightClick(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.contextClick(element).perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.ACTION_SUCCESS_MESSAGE, "right-click", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.ACTION_FAILED_MESSAGE, "right-click", locator.toString());
        }
    }

    public void click(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeClickable(locator);
            actions.moveToElement(element).click().perform();
            ReporterUtil.resultsReporter(locator, LogLevel.INFO, LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click", locator.toString());
        } catch (Exception e) {
            ReporterUtil.resultsReporter(locator, LogLevel.ERROR, LogMessageUtil.ACTION_FAILED_MESSAGE, "click", locator.toString());
        }
    }

    public void click(WebElement element) {
        try {
            waitUtil.waitForElementToBeVisible(getByFromElement(element)); // symbolic/logging only
            actions.moveToElement(element).click().perform();
            ReporterUtil.resultsReporter(null, LogLevel.INFO, LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click", "WebElement");
        } catch (Exception e) {
            ReporterUtil.resultsReporter(null, LogLevel.ERROR, LogMessageUtil.ACTION_FAILED_MESSAGE, "click", "WebElement");
        }
    }

    public void clickAnyContactRow() {
        By contactRowLocator = By.cssSelector(".contactTableBodyRow");
        try {
            WebElement contactRow = waitUtil.waitForElementToBeVisible(contactRowLocator);
            contactRow.click();
            ReporterUtil.resultsReporter(contactRowLocator, LogLevel.INFO, LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click", "any contact row");
        } catch (Exception e) {
            ReporterUtil.resultsReporter(contactRowLocator, LogLevel.ERROR, LogMessageUtil.ACTION_FAILED_MESSAGE, "click", "any contact row");
        }
    }

    /**
     * Symbolic locator used only for log message context.
     */
    private By getByFromElement(WebElement element) {
        return By.xpath("."); // symbolic
    }
}