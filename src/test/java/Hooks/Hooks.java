package Hooks;

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

import com.cheq.contactlist.utils.ConfigReader;
import com.cheq.contactlist.utils.DriverFactory;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;
import com.cheq.contactlist.utils.WaitUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private WebDriver driver;
    private WaitUtil waitUtil;
    private ScreenshotUtil screenshotUtil;
    private ConfigReader configReaderUtil;
    private String screenshotStatus;
    private int stepIndex = 1;
    private static ReporterUtil reporterUtil;
    
    @Before
    public void setUp(Scenario scenario) throws IOException, InterruptedException {
        System.out.println("Inside Hook - before scenario");

        ReporterUtil.logInfo("🚀 STARTING SCENARIO: " + extractFeatureName(scenario) + " - " + scenario.getName());
        ReporterUtil.logInfo("📌 Tags: " + scenario.getSourceTagNames());
        
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
                // Take screenshot with step count
                screenshotUtil.takeFullScreenScreenshotWithRobot("Step_" + stepIndex++);
                
                String latestScreenshotFolder = screenshotUtil.getScenarioFolderPath();
                if (latestScreenshotFolder != null) {
                    Path path = Paths.get(latestScreenshotFolder);
                    if (path.toFile().exists()) {
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

                    try {
                        WebElement errorElement = driver.findElement(By.className("error"));
                        // Take screenshot on failure
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenarioName);
                    } catch (NoSuchElementException e) {
                        ReporterUtil.logWarn("⚠️ No error element found; taking fallback screenshot");
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenarioName);
                    }
                } else {
                    ReporterUtil.logInfo("✅ Scenario PASSED: " + scenarioName);
                }

                // Final screenshot after scenario
                screenshotUtil.takeFullScreenScreenshotWithRobot("Final_" + scenarioName);

            } catch (Exception e) {
                ReporterUtil.logError("❌ Error during @After hook: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }

        saveScenarioResult(scenario);
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
    public static ReporterUtil getReporterUtil() {
        return reporterUtil;
    }
   }