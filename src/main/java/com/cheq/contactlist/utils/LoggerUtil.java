package com.cheq.contactlist.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for centralized logging throughout the Contact List Automation framework.
 * Uses Java's built-in logging API and provides simplified static methods for logging.
 *
 */
public class LoggerUtil {

	public static Logger logger;
    private static FileHandler fileHandler;
    private static String BASE_LOG_FOLDER;

    /**
     * Initializes the logger and sets up the log file handler
     */
    public synchronized static void setupLogger() {
        if (logger != null) return;

        try {
            BASE_LOG_FOLDER = ConfigReader.get("logs_folder");
            if (BASE_LOG_FOLDER == null || BASE_LOG_FOLDER.isEmpty()) {
                BASE_LOG_FOLDER = "logs"; // Fallback default
            }

            createLogFolder();

            String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String logFilePath = BASE_LOG_FOLDER + File.separator + date + "_logs.txt";

            logger = Logger.getLogger(LoggerUtil.class.getName());

            // Remove existing handlers to avoid duplicates
            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }

            fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date(record.getMillis()));
                    String logLevel = record.getLevel().getLocalizedName();
                    String message = formatMessage(record);
                    return String.format("%s - %s - %s%n", timestamp, logLevel, message);
                }
            });

            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);

            String formattedDate = new SimpleDateFormat("MMMM dd, yyyy hh:mm a").format(new Date());

            logSeparator("Start of Test");
            logger.info(String.format("` Date : %s `", formattedDate));
            logSeparator("");

        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates the log folder if it does not exist
     */
    private static void createLogFolder() {
        File logFolder = new File(BASE_LOG_FOLDER);
        if (!logFolder.exists() && !logFolder.mkdirs()) {
            throw new RuntimeException("Failed to create log directory: " + logFolder.getAbsolutePath());
        }
    }

    /**
     * Logs a message with the specified log level and the predefined log message.
     */
    public static void logMessage(String logLevel, String logMsg) {
        if (logger != null) {
            switch (logLevel.toUpperCase()) {
                case "INFO":
                    logger.info(logMsg);
                    break;
                case "SEVERE":
                case "ERROR":
                    logger.severe(logMsg);
                    break;
                case "WARNING":
                    logger.warning(logMsg);
                    break;
                default:
                    logger.info("Unknown log level: " + logMsg);
                    break;
            }
        }
    }

    /**
     * Logs and throws a RuntimeException with the given log message and log level
     */
    public static void logAndThrow(String logLevel, String logMsg, Object... args) {
        String formattedMsg = (args != null && args.length > 0) ? String.format(logMsg, args) : logMsg;

        logMessage(logLevel, formattedMsg); // ✅ already formatted
        throw new RuntimeException(String.format("[%s] - %s", logLevel.toUpperCase(), formattedMsg));
    }

    public static void info(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }
    /**
     * Closes the logger and releases resources
     */
    public static void closeLogger() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }

    /**
     * Logs a separator line with a custom label
     */
    private static void logSeparator(String label) {
        String separator = "------------------------------------------------";
        if (!label.isEmpty()) {
            logger.info(separator);
            logger.info(String.format("` %s `", label));
        }
        logger.info(separator);
    }
    
    public synchronized static void setupLogger(String scenarioName) {
        if (logger != null) {
            return; // Logger already initialized
        }
 
        try {
            ConfigReader configReader = new ConfigReader();
            Properties property = configReader.initProperty();
            BASE_LOG_FOLDER = property.getProperty("logs_folder");
 
            if (BASE_LOG_FOLDER == null || BASE_LOG_FOLDER.trim().isEmpty()) {
                BASE_LOG_FOLDER = "target/logs"; // fallback path
            }
 
            String scenarioFolderPath = createLogFolder(scenarioName);
            String logFilePath = scenarioFolderPath + File.separator + "execution.log";
 
            logger = Logger.getLogger(LoggerUtil.class.getName());
 
            // Remove existing handlers
            for (Handler handler : logger.getHandlers()) {
                logger.removeHandler(handler);
            }
 
            fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(new Date(record.getMillis()));
                    String logLevel = record.getLevel().getLocalizedName();
                    String message = formatMessage(record);
                    return String.format("%s - %s - %s%n", timestamp, logLevel, message);
                }
            });
 
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
 
            // Optional: also log to console
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);
 
            String formattedDate = new SimpleDateFormat("MMMM dd, yyyy hh:mm a").format(new Date());
 
            logSeparator("Start of Test");
            logger.info(String.format("` Date : %s `", formattedDate));
            logger.info(String.format("` Scenario : %s `", scenarioName));
            logSeparator("");
 
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a scenario-specific log folder with timestamp.
     */
    private static String createLogFolder(String scenarioName) {
        if (scenarioName == null || scenarioName.trim().isEmpty()) {
            scenarioName = "UnknownScenario";
        }

        scenarioName = scenarioName.replaceAll("[^a-zA-Z0-9-_]", "_");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String folderName = scenarioName + "_" + timestamp;
        String fullPath = BASE_LOG_FOLDER + File.separator + folderName;

        File folder = new File(fullPath);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Failed to create log directory: " + folder.getAbsolutePath());
        }

        return fullPath;
    }
}