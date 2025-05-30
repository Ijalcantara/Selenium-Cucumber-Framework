package Hooks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.cheq.contactlist.utils.ConfigReader;
import com.cheq.contactlist.utils.DriverFactory;
import com.cheq.contactlist.utils.ExtentReportUtil;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;
import com.cheq.contactlist.utils.WaitUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
//	private WebDriver driver;
//    private WaitUtil waitUtil;
//    private int stepIndex = 1;
//    private static boolean reportInitialized = false;
//
//    @Before
//    public void setUp(Scenario scenario) throws InterruptedException {
//        System.out.println("Inside Hook - before scenario");
//
//        if (!reportInitialized) {
//            ExtentReportUtil.initReport();
//            reportInitialized = true;
//        }
//
//        ExtentReportUtil.startTest(scenario.getName());
//
//        driver = DriverFactory.initDriver();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//        waitUtil = new WaitUtil(driver);
//
//        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
//        ReporterUtil.logInfo("Navigated to home page.");
//    }
//
//    @AfterStep
//    public void afterEachStep(Scenario scenario) {
//        if (driver != null) {
//            try {
//                String stepLabel = "Step_" + stepIndex;
//                String feature = extractFeatureName(scenario);
//                String scenarioName = scenario.getName();
//
//                String screenshotPath = ScreenshotUtil.takeScreenshot(driver, feature, scenarioName + "_" + stepLabel);
//
//                if (screenshotPath != null) {
//                    byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
//                    scenario.attach(screenshotBytes, "image/png", "📸 Screenshot for " + stepLabel);
//
//                    ExtentReportUtil.logStepWithScreenshot("➡️ " + stepLabel + " executed", screenshotPath);
//                }
//            } catch (IOException e) {
//                ReporterUtil.logWarn("⚠️ Failed to read screenshot file: " + e.getMessage());
//            } catch (Exception e) {
//                ReporterUtil.logWarn("⚠️ Failed to capture step screenshot: " + e.getMessage());
//            } finally {
//                stepIndex++;
//            }
//        }
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        System.out.println("Inside Hook - after scenario");
//
//        if (driver != null) {
//            try {
//                waitUtil.waitForElementToBeVisible(By.tagName("body"));
//                Thread.sleep(1000); // optional wait for stability
//
//                String feature = extractFeatureName(scenario);
//                String scenarioName = scenario.getName();
//
//                if (scenario.isFailed()) {
//                    ReporterUtil.logError("❌ Scenario FAILED: " + scenarioName);
//                    ExtentReportUtil.log(Status.FAIL, "Scenario failed.");
//
//                    try {
//                        WebElement errorElement = driver.findElement(By.className("error"));
//                        String highlightPath = ScreenshotUtil.takeScreenshotWithHighlight(driver, errorElement, feature, scenarioName);
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Highlighted failure element", highlightPath);
//                    } catch (NoSuchElementException e) {
//                        ReporterUtil.logWarn("⚠️ No highlightable element found. Taking fallback screenshot.");
//                        String fallbackPath = ScreenshotUtil.takeScreenshot(driver, feature, scenarioName + "_failed");
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Fallback screenshot on failure", fallbackPath);
//                    }
//
//                } else {
//                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
//                    ExtentReportUtil.log(Status.PASS, "Scenario passed.");
//                }
//
//                String finalScreenshotPath = ScreenshotUtil.takeScreenshot(driver, feature, scenarioName + "_final");
//                if (finalScreenshotPath != null) {
//                    ExtentReportUtil.logScreenshot(Status.INFO, "📸 Final screenshot after scenario", finalScreenshotPath);
//                }
//
//            } catch (Exception e) {
//                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
//            } finally {
//                driver.quit();
//            }
//        }
//
//        saveScenarioResult(scenario);
//        ExtentReportUtil.flushReport();
//    }
//
//    private void saveScenarioResult(Scenario scenario) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("scenarioName", scenario.getName());
//        result.put("status", scenario.getStatus().toString());
//        result.put("tags", scenario.getSourceTagNames());
//
//        String fileName = "target/reports/scenario-result.json";
//
//        try (FileWriter fileWriter = new FileWriter(fileName)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
//            System.out.println("Scenario result saved to: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Failed to write scenario result: " + e.getMessage());
//        }
//    }
//
//    private String extractFeatureName(Scenario scenario) {
//        try {
//            Path uri = Paths.get(scenario.getUri());
//            return uri.getFileName().toString().replace(".feature", "");
//        } catch (Exception e) {
//            return "UnknownFeature";
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public WaitUtil getWaitUtil() {
//        return waitUtil;
//    }
	
//	private WebDriver driver;
//    private WaitUtil waitUtil;
//    private ScreenshotUtil screenshotUtil;
//    private int stepIndex = 1;
//    private static boolean reportInitialized = false;
//
//    @Before
//    public void setUp(Scenario scenario) throws IOException, InterruptedException {
//        System.out.println("Inside Hook - before scenario");
//
//        if (!reportInitialized) {
//            ExtentReportUtil.initReport();
//            reportInitialized = true;
//        }
//
//        ExtentReportUtil.startTest(scenario.getName());
//
//        driver = DriverFactory.initDriver();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//        waitUtil = new WaitUtil(driver);
//
//        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
//        ReporterUtil.logInfo("Navigated to home page.");
//
//        // Initialize ScreenshotUtil instance and folders
//        screenshotUtil = new ScreenshotUtil(driver);
//        String baseFolder = System.getProperty("user.dir") + "/screenshots";
//        screenshotUtil.initializeScenarioFolder(extractFeatureName(scenario), scenario.getName(), baseFolder);
//
//        stepIndex = 1; // Reset step count for new scenario
//    }
//
//    @AfterStep
//    public void afterEachStep(Scenario scenario) {
//        if (driver != null) {
//            try {
//                String stepLabel = "Step_" + stepIndex++;
//                screenshotUtil.takeFullScreenScreenshotWithRobot(stepLabel);
//
//                // Attach screenshot to report
//                String screenshotFolder = screenshotUtil.getScenarioFolderPath();
//                Path screenshotPath = Paths.get(screenshotFolder, stepLabel + "_browser.png");
//
//                if (Files.exists(screenshotPath)) {
//                    byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
//                    scenario.attach(screenshotBytes, "image/png", "📸 Screenshot for " + stepLabel);
//                    ExtentReportUtil.logStepWithScreenshot("➡️ " + stepLabel + " executed", screenshotPath.toString());
//                }
//            } catch (IOException e) {
//                ReporterUtil.logWarn("⚠️ Failed to read screenshot file: " + e.getMessage());
//            } catch (Exception e) {
//                ReporterUtil.logWarn("⚠️ Failed to capture step screenshot: " + e.getMessage());
//            }
//        }
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        System.out.println("Inside Hook - after scenario");
//
//        if (driver != null) {
//            try {
//                waitUtil.waitForElementToBeVisible(By.tagName("body"));
//                Thread.sleep(1000); // optional wait for stability
//
//                String feature = extractFeatureName(scenario);
//                String scenarioName = scenario.getName();
//
//                if (scenario.isFailed()) {
//                    ReporterUtil.logError("❌ Scenario FAILED: " + scenarioName);
//                    ExtentReportUtil.log(Status.FAIL, "Scenario failed.");
//
//                    try {
//                        WebElement errorElement = driver.findElement(By.className("error"));
//                        String highlightPath = screenshotUtil.takeScreenshotWithHighlight(driver, errorElement, feature, scenarioName);
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Highlighted failure element", highlightPath);
//                    } catch (NoSuchElementException e) {
//                        ReporterUtil.logWarn("⚠️ No highlightable element found. Taking fallback screenshot.");
//                        String fallbackPath = screenshotUtil.takeScreenshot(driver, feature, scenarioName + "_failed");
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Fallback screenshot on failure", fallbackPath);
//                    }
//
//                } else {
//                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
//                    ExtentReportUtil.log(Status.PASS, "Scenario passed.");
//                }
//
//                // Final screenshot after scenario
//                String finalScreenshotPath = screenshotUtil.takeScreenshot(driver, feature, scenarioName + "_final");
//                if (finalScreenshotPath != null) {
//                    ExtentReportUtil.logScreenshot(Status.INFO, "📸 Final screenshot after scenario", finalScreenshotPath);
//                }
//
//            } catch (Exception e) {
//                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
//            } finally {
//                driver.quit();
//            }
//        }
//
//        saveScenarioResult(scenario);
//        ExtentReportUtil.flushReport();
//    }
//
//    private void saveScenarioResult(Scenario scenario) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("scenarioName", scenario.getName());
//        result.put("status", scenario.getStatus().toString());
//        result.put("tags", scenario.getSourceTagNames());
//
//        String fileName = "target/reports/scenario-result.json";
//
//        try (FileWriter fileWriter = new FileWriter(fileName)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
//            System.out.println("Scenario result saved to: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Failed to write scenario result: " + e.getMessage());
//        }
//    }
//
//    private String extractFeatureName(Scenario scenario) {
//        try {
//            Path uri = Paths.get(scenario.getUri());
//            return uri.getFileName().toString().replace(".feature", "");
//        } catch (Exception e) {
//            return "UnknownFeature";
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public WaitUtil getWaitUtil() {
//        return waitUtil;
//    }
//	
//	 private WebDriver driver;
//	    private WaitUtil waitUtil;
//	    private ScreenshotUtil screenshotUtil;
//	    private int stepIndex = 1;
//	    private static boolean reportInitialized = false;
//
//	    @Before
//	    public void setUp(Scenario scenario) throws IOException, InterruptedException {
//	        System.out.println("Inside Hook - before scenario");
//
//	        if (!reportInitialized) {
//	            ExtentReportUtil.initReport();
//	            reportInitialized = true;
//	        }
//
//	        ExtentReportUtil.startTest(scenario.getName());
//
//	        driver = DriverFactory.initDriver();
//	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//	        waitUtil = new WaitUtil(driver);
//
//	        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
//	        ReporterUtil.logInfo("Navigated to home page.");
//
//	        // Initialize ScreenshotUtil instance and folders
//	        screenshotUtil = new ScreenshotUtil(driver);
//	        String baseFolder = System.getProperty("user.dir") + "/screenshots";
//	        screenshotUtil.initializeScenarioFolder(extractFeatureName(scenario), scenario.getName(), baseFolder);
//
//	        stepIndex = 1; // Reset step count for new scenario
//	    }
//
//	    @AfterStep
//	    public void afterEachStep(Scenario scenario) {
//	        if (driver != null) {
//	            try {
//	                String stepLabel = "Step_" + stepIndex++;
//	                screenshotUtil.takeFullScreenScreenshotWithRobot(stepLabel);
//
//	                // Attach screenshot to report
//	                String screenshotFolder = screenshotUtil.getScenarioFolderPath();
//	                Path screenshotPath = Paths.get(screenshotFolder, stepLabel + "_browser.png");
//
//	                if (Files.exists(screenshotPath)) {
//	                    byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
//	                    scenario.attach(screenshotBytes, "image/png", "📸 Screenshot for " + stepLabel);
//	                    ExtentReportUtil.logStepWithScreenshot("➡️ " + stepLabel + " executed", screenshotPath.toString());
//	                }
//	            } catch (IOException e) {
//	                ReporterUtil.logWarn("⚠️ Failed to read screenshot file: " + e.getMessage());
//	            } catch (Exception e) {
//	                ReporterUtil.logWarn("⚠️ Failed to capture step screenshot: " + e.getMessage());
//	            }
//	        }
//	    }
//
//	    @After
//	    public void tearDown(Scenario scenario) {
//	        System.out.println("Inside Hook - after scenario");
//
//	        if (driver != null) {
//	            try {
//	                waitUtil.waitForElementToBeVisible(By.tagName("body"));
//	                Thread.sleep(1000); // optional wait for stability
//
//	                String feature = extractFeatureName(scenario);
//	                String scenarioName = scenario.getName();
//
//	                if (scenario.isFailed()) {
//	                    ReporterUtil.logError("❌ Scenario FAILED: " + scenarioName);
//	                    ExtentReportUtil.log(Status.FAIL, "Scenario failed.");
//
//	                    // No takeScreenshotWithHighlight method in your ScreenshotUtil,
//	                    // so just take a normal screenshot on failure here
//	                    try {
//	                        String fallbackPath = takeFallbackScreenshot(feature, scenarioName + "_failed");
//	                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Screenshot on failure", fallbackPath);
//	                    } catch (Exception e) {
//	                        ReporterUtil.logWarn("⚠️ Failed to capture failure screenshot: " + e.getMessage());
//	                    }
//	                } else {
//	                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
//	                    ExtentReportUtil.log(Status.PASS, "Scenario passed.");
//	                }
//
//	                // Take a final screenshot after scenario
//	                String finalScreenshotPath = takeFallbackScreenshot(feature, scenarioName + "_final");
//	                if (finalScreenshotPath != null) {
//	                    ExtentReportUtil.logScreenshot(Status.INFO, "📸 Final screenshot after scenario", finalScreenshotPath);
//	                }
//
//	            } catch (Exception e) {
//	                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
//	            } finally {
//	                driver.quit();
//	            }
//	        }
//
//	        saveScenarioResult(scenario);
//	        ExtentReportUtil.flushReport();
//	    }
//
//	    // Helper method to take a simple screenshot fallback on failure/final
//	    private String takeFallbackScreenshot(String feature, String screenshotName) {
//	        try {
//	            // Use ScreenshotUtil.takeFullScreenScreenshotWithRobot but it needs stepName param
//	            // We will save fallback/final screenshot in scenario folder
//	            // Manually create file path and copy the screenshot
//
//	            // Use driver to get screenshot
//	            File screenshotFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
//
//	            String baseFolder = System.getProperty("user.dir") + "/screenshots";
//	            String featureFolderPath = baseFolder + java.io.File.separator + feature;
//	            String scenarioFolderPath = featureFolderPath + java.io.File.separator + "Fallback";
//
//	            java.io.File featureFolder = new java.io.File(featureFolderPath);
//	            if (!featureFolder.exists()) featureFolder.mkdirs();
//
//	            java.io.File scenarioFolder = new java.io.File(scenarioFolderPath);
//	            if (!scenarioFolder.exists()) scenarioFolder.mkdirs();
//
//	            java.io.File destFile = new java.io.File(scenarioFolder, screenshotName + ".png");
//	            org.apache.commons.io.FileUtils.copyFile(screenshotFile, destFile);
//
//	            return destFile.getAbsolutePath();
//	        } catch (IOException e) {
//	            ReporterUtil.logWarn("⚠️ Failed to save fallback screenshot: " + e.getMessage());
//	            return null;
//	        }
//	    }
//
//	    private void saveScenarioResult(Scenario scenario) {
//	        Map<String, Object> result = new HashMap<>();
//	        result.put("scenarioName", scenario.getName());
//	        result.put("status", scenario.getStatus().toString());
//	        result.put("tags", scenario.getSourceTagNames());
//
//	        String fileName = "target/reports/scenario-result.json";
//
//	        try (FileWriter fileWriter = new FileWriter(fileName)) {
//	            ObjectMapper mapper = new ObjectMapper();
//	            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
//	            System.out.println("Scenario result saved to: " + fileName);
//	        } catch (IOException e) {
//	            System.err.println("Failed to write scenario result: " + e.getMessage());
//	        }
//	    }
//
//	    private String extractFeatureName(Scenario scenario) {
//	        try {
//	            Path uri = Paths.get(scenario.getUri());
//	            return uri.getFileName().toString().replace(".feature", "");
//	        } catch (Exception e) {
//	            return "UnknownFeature";
//	        }
//	    }
//
//	    public WebDriver getDriver() {
//	        return driver;
//	    }
//
//	    public WaitUtil getWaitUtil() {
//	        return waitUtil;
//	    }
	
	
//	private WebDriver driver;
//    private WaitUtil waitUtil;
//    private ScreenshotUtil screenshotUtil;
//    private int stepIndex = 1;
//    private static boolean reportInitialized = false;
//
//    @Before
//    public void setUp(Scenario scenario) throws IOException, InterruptedException {
//        System.out.println("Inside Hook - before scenario");
//
//        if (!reportInitialized) {
//            ExtentReportUtil.initReport();
//            reportInitialized = true;
//        }
//
//        ExtentReportUtil.startTest(scenario.getName());
//
//        driver = DriverFactory.initDriver();
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//        waitUtil = new WaitUtil(driver);
//
//        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
//        ReporterUtil.logInfo("Navigated to home page.");
//
//        screenshotUtil = new ScreenshotUtil(driver);
//        String baseFolder = System.getProperty("user.dir") + "/screenshots";
//        screenshotUtil.initializeScenarioFolder(extractFeatureName(scenario), scenario.getName(), baseFolder);
//
//        stepIndex = 1; // Reset step count for new scenario
//    }
//
//    @AfterStep
//    public void afterEachStep(Scenario scenario) {
//        if (driver != null) {
//            try {
//                String stepLabel = "Step_" + stepIndex++;
//                // Use browser screenshot (not full desktop) for consistency
//                String screenshotPath = screenshotUtil.takeBrowserScreenshot(stepLabel);
//
//                if (screenshotPath != null) {
//                    byte[] screenshotBytes = Files.readAllBytes(Paths.get(screenshotPath));
//                    scenario.attach(screenshotBytes, "image/png", "📸 Screenshot for " + stepLabel);
//                    ExtentReportUtil.logStepWithScreenshot("➡️ " + stepLabel + " executed", screenshotPath);
//                }
//            } catch (IOException e) {
//                ReporterUtil.logWarn("⚠️ Failed to read screenshot file: " + e.getMessage());
//            } catch (Exception e) {
//                ReporterUtil.logWarn("⚠️ Failed to capture step screenshot: " + e.getMessage());
//            }
//        }
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        System.out.println("Inside Hook - after scenario");
//
//        if (driver != null) {
//            try {
//                waitUtil.waitForElementToBeVisible(By.tagName("body"));
//                // No fixed sleep here, better wait is used
//
//                String feature = extractFeatureName(scenario);
//                String scenarioName = scenario.getName();
//
//                if (scenario.isFailed()) {
//                    ReporterUtil.logError("❌ Scenario FAILED: " + scenarioName);
//                    ExtentReportUtil.log(Status.FAIL, "Scenario failed.");
//
//                    try {
//                        WebElement errorElement = driver.findElement(By.className("error"));
//                        // Since highlight screenshot is not implemented, fallback:
//                        String fallbackPath = screenshotUtil.takeBrowserScreenshot("Failed_" + scenarioName);
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Screenshot on failure", fallbackPath);
//                    } catch (NoSuchElementException e) {
//                        ReporterUtil.logWarn("⚠️ No highlightable element found. Taking fallback screenshot.");
//                        String fallbackPath = screenshotUtil.takeBrowserScreenshot("Failed_" + scenarioName);
//                        ExtentReportUtil.logScreenshot(Status.FAIL, "📸 Screenshot on failure", fallbackPath);
//                    }
//
//                } else {
//                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
//                    ExtentReportUtil.log(Status.PASS, "Scenario passed.");
//                }
//
//                // Final screenshot after scenario
//                String finalScreenshotPath = screenshotUtil.takeBrowserScreenshot("Final_" + scenarioName);
//                if (finalScreenshotPath != null) {
//                    ExtentReportUtil.logScreenshot(Status.INFO, "📸 Final screenshot after scenario", finalScreenshotPath);
//                }
//
//            } catch (Exception e) {
//                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
//            } finally {
//                driver.quit();
//            }
//        }
//
//        saveScenarioResult(scenario);
//        ExtentReportUtil.flushReport();
//    }
//
//    private void saveScenarioResult(Scenario scenario) {
//        Map<String, Object> result = new HashMap<>();
//        result.put("scenarioName", scenario.getName());
//        result.put("status", scenario.getStatus().toString());
//        result.put("tags", scenario.getSourceTagNames());
//
//        String fileName = "target/reports/scenario-result.json";
//
//        try (FileWriter fileWriter = new FileWriter(fileName)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
//            System.out.println("Scenario result saved to: " + fileName);
//        } catch (IOException e) {
//            System.err.println("Failed to write scenario result: " + e.getMessage());
//        }
//    }
//
//    private String extractFeatureName(Scenario scenario) {
//        try {
//            Path uri = Paths.get(scenario.getUri());
//            return uri.getFileName().toString().replace(".feature", "");
//        } catch (Exception e) {
//            return "UnknownFeature";
//        }
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public WaitUtil getWaitUtil() {
//        return waitUtil;
//    }
	
	private WebDriver driver;
    private WaitUtil waitUtil;
    private ScreenshotUtil screenshotUtil;
    private ConfigReader configReaderUtil;
    private String screenshotStatus;
    private int stepIndex = 1;
    private static boolean reportInitialized = false;

    @Before
    public void setUp(Scenario scenario) throws IOException, InterruptedException {
        System.out.println("Inside Hook - before scenario");

        if (!reportInitialized) {
            ExtentReportUtil.initReport();
            reportInitialized = true;
        }
        ExtentReportUtil.startTest(scenario.getName());

        driver = DriverFactory.initDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        waitUtil = new WaitUtil(driver);

        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
        ReporterUtil.logInfo("Navigated to home page.");

        configReaderUtil = new ConfigReader();
        screenshotStatus = configReaderUtil.initProperty().getProperty("screenshot_status");

        screenshotUtil = new ScreenshotUtil(driver);
        String baseFolder = System.getProperty("user.dir") + "/screenshots";

        screenshotUtil.initializeScenarioFolder(extractFeatureName(scenario), scenario.getName(), baseFolder);

        stepIndex = 1; // Reset step count for new scenario
        screenshotUtil.resetCounter();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (driver != null && "On".equalsIgnoreCase(screenshotStatus)) {
            try {
                // Take screenshot using ScreenshotUtil with auto step count and naming
                screenshotUtil.takeFullScreenScreenshotWithRobot("Step_" + stepIndex++);
                // Attach latest screenshot to Cucumber report
                String latestScreenshotFolder = screenshotUtil.getScenarioFolderPath();
                if (latestScreenshotFolder != null) {
                    // Find latest screenshot file - this is optional, or you can build path directly
                    Path path = Paths.get(latestScreenshotFolder);
                    if (path.toFile().exists()) {
                        // Attach screenshot from last step
                        // Since ScreenshotUtil names screenshots with step number, build filename:
                        String screenshotFileName = String.format("%02d_Step_%d_browser.png", stepIndex - 1, stepIndex - 1);
                        Path screenshotPath = path.resolve(screenshotFileName);
                        if (Files.exists(screenshotPath)) {
                            byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
                            scenario.attach(screenshotBytes, "image/png", "Step " + (stepIndex - 1));
                        }
                    }
                }
            } catch (IOException e) {
                ReporterUtil.logWarn("⚠️ Failed to attach screenshot for step: " + e.getMessage());
            } catch (Exception e) {
                ReporterUtil.logWarn("⚠️ Error in after step hook: " + e.getMessage());
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Inside Hook - after scenario");

        if (driver != null) {
            try {
                waitUtil.waitForElementToBeVisible(By.tagName("body"));

                String scenarioName = scenario.getName();

                if (scenario.isFailed()) {
                    ReporterUtil.logError("❌ Scenario FAILED: " + scenarioName);
                    ExtentReportUtil.logFail("Scenario failed.");

                    try {
                        WebElement errorElement = driver.findElement(By.className("error"));
                        // No highlight screenshot; fallback to normal screenshot
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenarioName);
                        String failScreenshotPath = screenshotUtil.getScenarioFolderPath() + "/Failed_" + scenarioName + "_browser.png";
                        ExtentReportUtil.logScreenshotFail("Screenshot on failure", failScreenshotPath);
                    } catch (NoSuchElementException e) {
                        ReporterUtil.logWarn("⚠️ No error element found; taking fallback screenshot");
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenarioName);
                        String failScreenshotPath = screenshotUtil.getScenarioFolderPath() + "/Failed_" + scenarioName + "_browser.png";
                        ExtentReportUtil.logScreenshotFail("Screenshot on failure", failScreenshotPath);
                    }
                } else {
                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
                    ExtentReportUtil.logPass("Scenario passed.");
                }

                // Final screenshot after scenario
                screenshotUtil.takeFullScreenScreenshotWithRobot("Final_" + scenarioName);
                String finalScreenshotPath = screenshotUtil.getScenarioFolderPath() + "/Final_" + scenarioName + "_browser.png";
                ExtentReportUtil.logScreenshotInfo("Final screenshot", finalScreenshotPath);

            } catch (Exception e) {
                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }

        saveScenarioResult(scenario);
        ExtentReportUtil.flushReport();
    }

    private void saveScenarioResult(Scenario scenario) {
        Map<String, Object> result = new HashMap<>();
        result.put("scenarioName", scenario.getName());
        result.put("status", scenario.getStatus().toString());
        result.put("tags", scenario.getSourceTagNames());

        String fileName = "target/reports/scenario-result.json";

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
            System.out.println("Scenario result saved to: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to write scenario result: " + e.getMessage());
        }
    }

    private String extractFeatureName(Scenario scenario) {
        try {
            Path uri = Paths.get(scenario.getUri());
            return uri.getFileName().toString().replace(".feature", "");
        } catch (Exception e) {
            return "UnknownFeature";
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitUtil getWaitUtil() {
        return waitUtil;
    }
   }