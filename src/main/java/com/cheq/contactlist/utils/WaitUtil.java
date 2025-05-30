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

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        ReporterUtil.logInfo(LogMessageUtil.initialized("WaitUtil", DEFAULT_TIMEOUT));
    }

    public void pause(int seconds) {
        try {
            ReporterUtil.logInfo(LogMessageUtil.pause(seconds));
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ReporterUtil.logException("Interrupted during pause", e);
            throw new RuntimeException("Interrupted during pause", e);
        }
    }

    public WebElement waitForElementToBeVisible(By locator) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to be visible", locator));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ReporterUtil.logPass(LogMessageUtil.elementVisible(locator));
            return element;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.elementNotVisible(locator));
            throw new RuntimeException(LogMessageUtil.elementNotVisible(locator), e);
        }
    }

    public WebElement waitForElementToBeClickable(By locator) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to be clickable", locator));
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            ReporterUtil.logPass(LogMessageUtil.elementClickable(locator));
            return element;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.elementNotClickable(locator));
            throw new RuntimeException(LogMessageUtil.elementNotClickable(locator), e);
        }
    }

    public boolean waitForElementToDisappear(By locator) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to disappear", locator));
        try {
            boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            ReporterUtil.logPass(LogMessageUtil.elementDisappeared(locator));
            return result;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.elementDidNotDisappear(locator));
            throw new RuntimeException(LogMessageUtil.elementDidNotDisappear(locator), e);
        }
    }

    public boolean waitForUrlToContain(String partialUrl) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for URL to contain", partialUrl));
        try {
            boolean result = wait.until(ExpectedConditions.urlContains(partialUrl));
            ReporterUtil.logPass(LogMessageUtil.urlContains(partialUrl));
            return result;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.urlDoesNotContain(partialUrl));
            throw new RuntimeException(LogMessageUtil.urlDoesNotContain(partialUrl), e);
        }
    }

    public boolean waitForTextToBePresent(By locator, String text) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for text to be present", locator));
        try {
            boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            ReporterUtil.logPass(LogMessageUtil.textPresent(locator, text));
            return result;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.textNotPresent(locator, text));
            throw new RuntimeException(LogMessageUtil.textNotPresent(locator, text), e);
        }
    }

    public Alert waitForAlert() {
        return waitForAlert(DEFAULT_TIMEOUT);
    }

    public Alert waitForAlert(int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for alert", "timeout " + timeoutSeconds + " seconds"));
        try {
            Alert alert = customWait.until(ExpectedConditions.alertIsPresent());
            ReporterUtil.logPass(LogMessageUtil.alertPresent());
            return alert;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.alertNotPresent());
            throw new RuntimeException(LogMessageUtil.alertNotPresent(), e);
        }
    }
    
    public Alert waitForAlertOrNull(int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for alert", "timeout " + timeoutSeconds + " seconds"));
        try {
            Alert alert = customWait.until(ExpectedConditions.alertIsPresent());
            ReporterUtil.logPass(LogMessageUtil.alertPresent());
            return alert;
        } catch (TimeoutException e) {
            ReporterUtil.logFail(LogMessageUtil.alertNotPresent());
            return null;  // Return null instead of throwing
        }
    }

    public boolean waitForVisibility(By locator) {
        ReporterUtil.logInfo(LogMessageUtil.actionStarted("check visibility", locator));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ReporterUtil.logPass(LogMessageUtil.elementVisible(locator));
            return true;
        } catch (TimeoutException e) {
            ReporterUtil.logWarning(LogMessageUtil.waitVisibilityTimeout(locator));
            return false;
        }
    }

    public void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        ReporterUtil.logInfo(LogMessageUtil.implicitWaitSet(seconds));
    }

    public void setExplicitWait(int seconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        ReporterUtil.logInfo(LogMessageUtil.explicitWaitSet(seconds));
    }

    public void sleep(long millis) {
        try {
            ReporterUtil.logInfo(LogMessageUtil.sleep(millis));
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ReporterUtil.logException("Interrupted during sleep", e);
        }
    }
    
    public boolean isElementPresent(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private static final int DEFAULT_TIMEOUT = 10;
//
//    public WaitUtil(WebDriver driver) {
//        this.driver = driver;
//        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
//        ReporterUtil.logInfo(LogMessageUtil.initialized("WaitUtil", DEFAULT_TIMEOUT));
//    }
//
//    public void pause(int seconds) {
//        try {
//            ReporterUtil.logInfo(LogMessageUtil.pause(seconds));
//            Thread.sleep(seconds * 1000L);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            ReporterUtil.logException("Interrupted during pause", e);
//            throw new RuntimeException("Interrupted during pause", e);
//        }
//    }
//
//    public WebElement waitForElementToBeVisible(By locator) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to be visible", locator));
//        try {
//            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            ReporterUtil.logPass(LogMessageUtil.elementVisible(locator));
//            return element;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.elementNotVisible(locator));
//            throw new RuntimeException(LogMessageUtil.elementNotVisible(locator), e);
//        }
//    }
//
//    public WebElement waitForElementToBeClickable(By locator) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to be clickable", locator));
//        try {
//            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//            ReporterUtil.logPass(LogMessageUtil.elementClickable(locator));
//            return element;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.elementNotClickable(locator));
//            throw new RuntimeException(LogMessageUtil.elementNotClickable(locator), e);
//        }
//    }
//
//    public boolean waitForElementToDisappear(By locator) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for element to disappear", locator));
//        try {
//            boolean result = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
//            ReporterUtil.logPass(LogMessageUtil.elementDisappeared(locator));
//            return result;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.elementDidNotDisappear(locator));
//            throw new RuntimeException(LogMessageUtil.elementDidNotDisappear(locator), e);
//        }
//    }
//
//    public boolean waitForUrlToContain(String partialUrl) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for URL to contain", partialUrl));
//        try {
//            boolean result = wait.until(ExpectedConditions.urlContains(partialUrl));
//            ReporterUtil.logPass(LogMessageUtil.urlContains(partialUrl));
//            return result;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.urlDoesNotContain(partialUrl));
//            throw new RuntimeException(LogMessageUtil.urlDoesNotContain(partialUrl), e);
//        }
//    }
//
//    public boolean waitForTextToBePresent(By locator, String text) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for text to be present", locator));
//        try {
//            boolean result = wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
//            ReporterUtil.logPass(LogMessageUtil.textPresent(locator, text));
//            return result;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.textNotPresent(locator, text));
//            throw new RuntimeException(LogMessageUtil.textNotPresent(locator, text), e);
//        }
//    }
//
//    public Alert waitForAlert() {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("wait for alert", ""));
//        try {
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//            ReporterUtil.logPass(LogMessageUtil.alertPresent());
//            return alert;
//        } catch (TimeoutException e) {
//            ReporterUtil.logFail(LogMessageUtil.alertNotPresent());
//            throw new RuntimeException(LogMessageUtil.alertNotPresent(), e);
//        }
//    }
//
//    public boolean waitForVisibility(By locator) {
//        ReporterUtil.logInfo(LogMessageUtil.actionStarted("check visibility", locator));
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            ReporterUtil.logPass(LogMessageUtil.elementVisible(locator));
//            return true;
//        } catch (TimeoutException e) {
//            ReporterUtil.logWarning(LogMessageUtil.waitVisibilityTimeout(locator));
//            return false;
//        }
//    }
//
//    public void setImplicitWait(int seconds) {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
//        ReporterUtil.logInfo(LogMessageUtil.implicitWaitSet(seconds));
//    }
//
//    public void setExplicitWait(int seconds) {
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
//        ReporterUtil.logInfo(LogMessageUtil.explicitWaitSet(seconds));
//    }
//
//    public void sleep(long millis) {
//        try {
//            ReporterUtil.logInfo(LogMessageUtil.sleep(millis));
//            Thread.sleep(millis);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            ReporterUtil.logException("Interrupted during sleep", e);
//        }
//    }
}