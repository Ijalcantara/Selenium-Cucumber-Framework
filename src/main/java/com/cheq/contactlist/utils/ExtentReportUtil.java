package com.cheq.contactlist.utils;



import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {

	private static ExtentReports extent;
    private static ExtentTest parentTest;
    private static ExtentTest childTest;
    private static final String REPORT_PATH = "target/reports/ExtentReport.html";
    private static ExtentTest test;
    private static ExtentTest stepNode; // child node per step

    private ExtentReportUtil() {}

    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_PATH);
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "YourName");
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    public static void logScreenshotFail(String message, String screenshotPath) {
        logScreenshot(Status.FAIL, message, screenshotPath);
    }

    public static void logFail(String message) {
        log(Status.FAIL, message);
    }

    public static void logPass(String message) {
        log(Status.PASS, message);
    }
    public static void logScreenshotInfo(String message, String screenshotPath) {
        logScreenshot(Status.INFO, message, screenshotPath);
    }

    public static void startTest(String scenarioName) {
        parentTest = extent.createTest(scenarioName);
    }

    public static void logStepWithScreenshot(String message, String screenshotPath) {
        if (test != null && screenshotPath != null) {
            try {
                // Embed screenshot with custom width using HTML
                String htmlImage = "<img src='" + screenshotPath + "' width='800px' />";

                // Log message + embedded wide screenshot
                test.info(message + "<br>" + htmlImage);
            } catch (Exception e) {
                test.warning("⚠️ Failed to attach screenshot inline: " + e.getMessage());
            }
        } else if (test != null) {
            test.info(message);
        }
    }

    public static void logScreenshot(Status status, String message, String screenshotPath) {
        if (parentTest != null && screenshotPath != null) {
            try {
                parentTest.log(status, message,
                    MediaEntityBuilder.createScreenCaptureFromPath(Paths.get(screenshotPath).toString()).build());
            } catch (Exception e) {
                parentTest.warning("⚠️ Failed to attach screenshot: " + e.getMessage());
            }
        } else if (parentTest != null) {
            parentTest.log(status, message);
        }
    }

    public static void log(Status status, String message) {
        if (parentTest != null) {
            parentTest.log(status, message);
        }
    }

    public static ExtentTest getTest() {
        return parentTest;
    }
}
