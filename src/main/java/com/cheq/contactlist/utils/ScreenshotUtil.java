package com.cheq.contactlist.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

	private static final Logger LOGGER = Logger.getLogger(ScreenshotUtil.class.getName());

    private WebDriver driver;
    private ConfigReader configReaderUtil;
    private String scenarioFolderPath;
    private String screenshotStatus;
    private int stepCounter = 1;

    /** Constructor to initialize the ScreenshotUtil with WebDriver */
    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
        getProperty();
    }

    /** Loads properties from the configuration file */
    public void getProperty() {
        configReaderUtil = new ConfigReader();
        screenshotStatus = configReaderUtil.initProperty().getProperty("screenshot_status");
        LOGGER.info("Screenshot status loaded: " + screenshotStatus);
    }

    /** Initializes the scenario folder path under featureName and scenarioName */
    public void initializeScenarioFolder(String featureName, String scenarioName, String baseFolderPath) throws IOException {
        if (featureName == null || featureName.isEmpty() || scenarioName == null || scenarioName.isEmpty()) {
            throw new IllegalArgumentException("Feature name or scenario name cannot be null or empty");
        }

        if ("On".equalsIgnoreCase(screenshotStatus)) {
            // Create date folder with format yyyy-MM-dd
            String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String dateFolderPath = baseFolderPath + File.separator + dateFolder;
            String featureFolderPath = dateFolderPath + File.separator + featureName;
            this.scenarioFolderPath = featureFolderPath + File.separator + scenarioName;

            File dateFolderFile = new File(dateFolderPath);
            if (!dateFolderFile.exists()) {
                dateFolderFile.mkdirs();
            }

            File featureFolder = new File(featureFolderPath);
            if (!featureFolder.exists()) {
                featureFolder.mkdirs();
            }

            File scenarioFolder = new File(this.scenarioFolderPath);
            if (!scenarioFolder.exists()) {
                scenarioFolder.mkdirs();
            }
        }
    }

    /** Takes a browser-only screenshot using Selenium TakesScreenshot */
    public void takeFullScreenScreenshotWithRobot(String stepName) throws IOException {
        if (!"On".equalsIgnoreCase(screenshotStatus)) {
            LOGGER.info("Screenshot status is Off; skipping screenshot capture");
            return;
        }

        if (scenarioFolderPath == null || scenarioFolderPath.isEmpty()) {
            LOGGER.warning("Scenario folder path is null or empty, defaulting to 'target/screenshots/default'");
            scenarioFolderPath = "target/screenshots/default";
        }

        // Make folder absolute and ensure it exists
        File folder = new File(scenarioFolderPath);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            LOGGER.info("Created screenshot folder: " + created + " at " + folder.getAbsolutePath());
        }

        String formattedStep = String.format("%02d_%s", stepCounter++, stepName);
        String uniqueFileName = formattedStep + "_browser.png";

        File destinationFile = new File(folder, uniqueFileName);
        LOGGER.info("Saving screenshot to: " + destinationFile.getAbsolutePath());

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshot, destinationFile);

        LOGGER.info("Screenshot saved successfully.");
    }

    /** Resets the step counter to 1 */
    public void resetCounter() {
        stepCounter = 1;
    }

    /** Sets the status for screenshot capture ('On' or 'Off') */
    public void setScreenshotStatus(String status) {
        if ("On".equalsIgnoreCase(status) || "Off".equalsIgnoreCase(status)) {
            this.screenshotStatus = status;
        } else {
            throw new IllegalArgumentException("Invalid screenshot status. Use 'On' or 'Off'.");
        }
    }

    /** Returns the folder path where screenshots for the scenario are stored */
    public String getScenarioFolderPath() {
        return this.scenarioFolderPath;
    }

    /** Sets the scenario folder path manually (optional helper) */
    public void setScenarioFolderPath(String scenarioFolderPath) {
        this.scenarioFolderPath = scenarioFolderPath;
    }
}

