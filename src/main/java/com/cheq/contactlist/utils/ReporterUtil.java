package com.cheq.contactlist.utils;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReporterUtil {

//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    private static String getTimestamp() {
//        return sdf.format(new Date());
//    }
//
//    public static void logInfo(String message) {
//        LoggerUtil.info(getTimestamp() + " INFO: " + message);
//    }
//
//    public static void logWarning(String message) {
//        LoggerUtil.warn(getTimestamp() + " WARNING: " + message);
//    }
//
//    public static void logError(String message) {
//        LoggerUtil.error(getTimestamp() + " ERROR: " + message);
//    }
//
//    public static void logException(String message, Exception e) {
//        LoggerUtil.error(getTimestamp() + " EXCEPTION: " + message + " | " + e.getMessage());
//        e.printStackTrace();
//    }
//
//    public static void logPass(String message) {
//        LoggerUtil.info(getTimestamp() + " ✅ PASS: " + message);
//    }
//
//    public static void logFail(String message) {
//        LoggerUtil.error(getTimestamp() + " ❌ FAIL: " + message);
//    }
//
//    public static void logWarn(String message) {
//        LoggerUtil.warn(getTimestamp() + " WARNING: " + message);
//    }
//
//    public static String takeScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            String dest = "target/screenshots/" + screenshotName + ".png";
//            File destination = new File(dest);
//            FileUtils.copyFile(source, destination);
//            LoggerUtil.info("📸 Screenshot saved: " + dest);
//            return dest;
//        } catch (Exception e) {
//            LoggerUtil.error("❌ Failed to capture screenshot: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public static void takeScreenshotWithHighlight(WebDriver driver, WebElement element, String feature, String scenario) {
//        try {
//            LoggerUtil.info("Taking highlighted screenshot for feature: " + feature + ", scenario: " + scenario);
//            ScreenshotUtil.takeScreenshotWithHighlight(driver, element, feature, scenario); // ✅ make sure this method exists
//        } catch (Exception e) {
//            LoggerUtil.error("Failed to take highlighted screenshot: " + e.getMessage());
//        }
//    }
//
//    public static void resultsReporter(Object locator, String logLevel, String messageTemplate, Object... args) {
//        String formattedMessage = String.format(messageTemplate, args);
//
//        if (locator != null) {
//            formattedMessage += " | Locator: " + locator.toString();
//        }
//
//        LoggerUtil.log(logLevel, formattedMessage);
//    }
	
	private WebDriver driver;
	 Properties property;
	 
	    private ScreenshotUtil screenshotUtil;   
	    private ConfigReader configReaderUtil;
	    
	    private String captureScreenshot;

	    /** Initializes ReporterUtil with WebDriver and ScreenshotUtil */
	    public ReporterUtil(WebDriver driver, ScreenshotUtil screenshotUtil) {
	        this.driver = driver;
	        this.screenshotUtil = screenshotUtil;
	        getProperty();
	    }

	    /** Initializes properties and the JSON file. */
//	    public void getProperty() {
//	        configReaderUtil = new ConfigReader();
//	        property = configReaderUtil.initProperty();
//	        captureScreenshot = property.getProperty("screenshot_status");
//	    }
	    
	    public void getProperty() {
	        captureScreenshot = ConfigReader.get("screenshot_status");
	    }

	    /** Captures a screenshot and logs a message for the given test step 
	     * @throws AWTException */
	    public void resultsReporter(By elementName, String logLevel, String logMsg, String elementDetails) throws IOException, AWTException {
	     
	        String stepName = getScreenshotDatetime();
	        
	        if ("info".equalsIgnoreCase(logLevel)) {
	            LoggerUtil.logMessage(logLevel, logMsg, elementDetails);
	            if ("On".equalsIgnoreCase(captureScreenshot)) {
	             screenshotUtil.takeFullScreenScreenshotWithRobot(stepName);
	            }
	        } else {
	            if ("On".equalsIgnoreCase(captureScreenshot)) {
	               screenshotUtil.takeFullScreenScreenshotWithRobot(stepName);
	            }
	            LoggerUtil.logAndThrow(logLevel, logMsg, elementDetails);
	        }

	    }
	    
	    /** Logs messages based on the log level for API calls. */
	    public void resultsReporterAPI(String logLevel, String logMsg, String elementDetails) throws IOException {
	     
	        if ("info".equalsIgnoreCase(logLevel)) {
	            LoggerUtil.logMessage(logLevel, logMsg, elementDetails);
	        } else {
	            LoggerUtil.logAndThrow(logLevel, logMsg, elementDetails);
	        }
	    }

	    /** Generates a timestamped screenshot name based on current date and time. */
	    private String getScreenshotDatetime() {
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
	        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmssSSS");
	        String formattedDate = currentDateTime.format(dateFormatter);
	        String formattedTime = currentDateTime.format(timeFormatter);
	        
	        return "screenshot_" + formattedDate + "_" + formattedTime;
	    }
}
