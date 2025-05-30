package com.cheq.contactlist.utils;

import java.io.IOException;

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
    private ReporterUtil reporterUtil;

    /**
     * Constructor to initialize mouse actions and utilities.
     *
     * @param driver         The WebDriver instance to be used for performing actions
     * @param reporterUtil   The ReporterUtil instance for logging and screenshot capturing
     * @throws IOException 
     */
    public MouseActionUtil(WebDriver driver, ReporterUtil reporterUtil) throws IOException {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waitUtil = new WaitUtil(driver);
        this.reporterUtil = reporterUtil;

        try {
            reporterUtil.resultsReporter(null, "info", LogMessageUtil.CLASS_INITIALIZED_MESSAGE, "MouseActionUtil");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hover(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.moveToElement(element).perform();
            reporterUtil.resultsReporter(locator, "info", LogMessageUtil.ACTION_SUCCESS_MESSAGE, "hover: " + locator.toString());
        } catch (Exception e) {
            handleException(locator, "hover");
        }
    }

    public void rightClick(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeVisible(locator);
            actions.contextClick(element).perform();
            reporterUtil.resultsReporter(locator, "info", LogMessageUtil.ACTION_SUCCESS_MESSAGE, "right-click: " + locator.toString());
        } catch (Exception e) {
            handleException(locator, "right-click");
        }
    }

    public void click(By locator) {
        try {
            WebElement element = waitUtil.waitForElementToBeClickable(locator);
            actions.moveToElement(element).click().perform();
            reporterUtil.resultsReporter(locator, "info", LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click: " + locator.toString());
        } catch (Exception e) {
            handleException(locator, "click");
        }
    }

    public void click(WebElement element) {
        try {
            waitUtil.waitForElementToBeVisible(getByFromElement(element)); // symbolic/logging only
            actions.moveToElement(element).click().perform();
            reporterUtil.resultsReporter(null, "info", LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click: WebElement");
        } catch (Exception e) {
            handleException(null, "click: WebElement");
        }
    }

    public void clickAnyContactRow() {
        By contactRowLocator = By.cssSelector(".contactTableBodyRow");
        try {
            WebElement contactRow = waitUtil.waitForElementToBeVisible(contactRowLocator);
            contactRow.click();
            reporterUtil.resultsReporter(contactRowLocator, "info", LogMessageUtil.ACTION_SUCCESS_MESSAGE, "click: any contact row");
        } catch (Exception e) {
            handleException(contactRowLocator, "click: any contact row");
        }
    }

    /**
     * Handles exceptions with logging and screenshot if enabled.
     */
    private void handleException(By locator, String actionName) {
        try {
            reporterUtil.resultsReporter(locator, "error", LogMessageUtil.ACTION_FAILED_MESSAGE, actionName + (locator != null ? ": " + locator.toString() : ""));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Symbolic locator used only for log message context.
     */
    private By getByFromElement(WebElement element) {
        return By.xpath("."); // symbolic placeholder
    }
}