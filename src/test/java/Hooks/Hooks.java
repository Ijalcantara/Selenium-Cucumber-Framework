package Hooks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cheq.contactlist.utils.DriverFactory;
import com.cheq.contactlist.utils.WaitUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	private WebDriver driver;
    private WaitUtil waitUtil;

    @Before
    public void setUp() throws InterruptedException {
        System.out.println("Inside Hook - before scenario");
        
        driver = DriverFactory.initDriver(); 
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        waitUtil = new WaitUtil(driver);

        driver.navigate().to("https://thinking-tester-contact-list.herokuapp.com/");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Inside Hook - after scenario");

        saveScenarioResult(scenario);

        // Attach file contents to scenario report
        try {
            Path logPath = Paths.get("target/reports/scenario-result.json");
            if (Files.exists(logPath)) {
                byte[] data = Files.readAllBytes(logPath);
                scenario.attach(data, "application/json", "Scenario Result JSON");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            waitUtil.waitForElementToBeVisible(By.tagName("body"));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WaitUtil getWaitUtil() {
        return waitUtil;
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
}
