//package com.cheq.contactlist.utils;
//
//package com.cheq.contactlist.utils;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.cucumber.java.Scenario;
//
//public class ReporterUtil {
//
//    private static final String REPORT_DIR = "target/reports/";
//
//    /**
//     * Logs scenario result to JSON.
//     */
//    public static void logScenarioResult(Scenario scenario) {
//        Map<String, Object> result = new LinkedHashMap<>();
//        result.put("scenarioName", scenario.getName());
//        result.put("status", scenario.getStatus().toString());
//        result.put("tags", scenario.getSourceTagNames());
//        result.put("timestamp", getTimestamp());
//
//        String fileName = REPORT_DIR + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".json";
//
//        try (FileWriter fileWriter = new FileWriter(fileName)) {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, result);
//            System.out.println("✅ Scenario result saved to: " + fileName);
//        } catch (IOException e) {
//            System.err.println("❌ Failed to write scenario result: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Takes and saves a screenshot if the scenario fails.
//     */
//    public static void captureScreenshotOnFailure(WebDriver driver, Scenario scenario) {
//        if (scenario.isFailed()) {
//            try {
//                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                String screenshotName = REPORT_DIR + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
//                File destFile = new File(screenshotName);
//                screenshotFile.renameTo(destFile);
//                scenario.attach(screenshotFile, "image/png", "Screenshot");
//                System.out.println("📷 Screenshot saved to: " + screenshotName);
//            } catch (Exception e) {
//                System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
//            }
//        }
//    }
//
//    private static String getTimestamp() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//}
