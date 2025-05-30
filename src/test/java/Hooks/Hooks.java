package Hooks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.ConfigReader;
import com.cheq.contactlist.utils.DriverFactory;
import com.cheq.contactlist.utils.LoggerUtil;  // <-- import your LoggerUtil
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;
import com.cheq.contactlist.utils.WaitUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    private static final String REPORT_FILE = "target/reports/scenario-results.json";
    private static ReporterUtil reporterUtil;


    private static ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    @Before
    public void setUp(Scenario scenario) throws IOException {
        String scenarioName = scenario.getName();

        // Setup per-scenario logger
        LoggerUtil.setupLogger(scenarioName);

        LoggerUtil.logger.info("🚀 STARTING SCENARIO: " + extractFeatureName(scenario) + " - " + scenarioName);
        LoggerUtil.logger.info("📌 Tags: " + scenario.getSourceTagNames());

        driver = DriverFactory.initDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        waitUtil = new WaitUtil(driver);

        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
        LoggerUtil.logger.info("Navigated to home page.");

        configReaderUtil = new ConfigReader();
        screenshotStatus = configReaderUtil.initProperty().getProperty("screenshot_status");

        screenshotUtil = new ScreenshotUtil(driver);
        String baseFolder = System.getProperty("user.dir") + "/screenshots";
        screenshotUtil.initializeScenarioFolder(extractFeatureName(scenario), scenarioName, baseFolder);

        stepIndex = 1;
        screenshotUtil.resetCounter();
    }

    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (driver != null && "On".equalsIgnoreCase(screenshotStatus)) {
            try {
                screenshotUtil.takeFullScreenScreenshotWithRobot("Step_" + stepIndex);
                attachLatestScreenshotToScenario(screenshotUtil.getScenarioFolderPath(), stepIndex, scenario);
                LoggerUtil.logger.info("📝 Logged Step " + stepIndex + " for scenario: " + scenario.getName());
                stepIndex++;
            } catch (Exception e) {
                LoggerUtil.logger.warning("⚠️ Error capturing screenshot on step " + stepIndex + ": " + e.getMessage());
            }
        }
    }

    private void attachLatestScreenshotToScenario(String folderPath, int stepNumber, Scenario scenario) throws IOException {
        if (folderPath == null) return;
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) return;

        String screenshotFileName = String.format("%02d_Step_%d_browser.png", stepNumber, stepNumber);
        Path screenshotPath = path.resolve(screenshotFileName);

        if (Files.exists(screenshotPath)) {
            byte[] screenshotBytes = Files.readAllBytes(screenshotPath);
            scenario.attach(screenshotBytes, "image/png", "Step " + stepNumber);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        LoggerUtil.logger.info("Inside Hook - after scenario: " + scenario.getName());

        if (driver != null) {
            try {
                waitUtil.waitForElementToBeVisible(By.tagName("body"));

                if (scenario.isFailed()) {
                    LoggerUtil.logger.severe("❌ Scenario FAILED: " + scenario.getName());

                    try {
                        WebElement errorElement = driver.findElement(By.className("error"));
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenario.getName());
                    } catch (NoSuchElementException e) {
                        LoggerUtil.logger.warning("⚠️ No error element found; taking fallback screenshot");
                        screenshotUtil.takeFullScreenScreenshotWithRobot("Failed_" + scenario.getName());
                    }

                } else {
                    LoggerUtil.logger.info("✅ Scenario PASSED: " + scenario.getName());
                }

                screenshotUtil.takeFullScreenScreenshotWithRobot("Final_" + scenario.getName());

            } catch (Exception e) {
                LoggerUtil.logger.severe("❌ Error during @After hook: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }

        try {
            saveScenarioResult(scenario);
        } catch (Exception e) {
            LoggerUtil.logger.severe("❌ Failed to save scenario result: " + e.getMessage());
        }
    }

    private synchronized void saveScenarioResult(Scenario scenario) throws IOException {
        File reportFile = new File(REPORT_FILE);
        ArrayNode scenarioResults;

        if (reportFile.exists()) {
            scenarioResults = (ArrayNode) mapper.readTree(reportFile);
        } else {
            scenarioResults = mapper.createArrayNode();
        }

        ObjectNode scenarioNode = mapper.createObjectNode();
        scenarioNode.put("scenarioName", scenario.getName());
        scenarioNode.put("featureName", extractFeatureName(scenario));
        scenarioNode.put("status", scenario.getStatus().toString());
        scenarioNode.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        ArrayNode tagsNode = mapper.createArrayNode();
        scenario.getSourceTagNames().forEach(tagsNode::add);
        scenarioNode.set("tags", tagsNode);

        scenarioResults.add(scenarioNode);

        File parentDir = reportFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(reportFile)) {
            mapper.writeValue(fileWriter, scenarioResults);
        }

        LoggerUtil.info("Scenario result saved to: " + REPORT_FILE);
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