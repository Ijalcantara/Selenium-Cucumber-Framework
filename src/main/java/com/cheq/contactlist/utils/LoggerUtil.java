package com.cheq.contactlist.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {

	private static Logger logger;

    static {
        logger = Logger.getLogger("ContactListAutomationLogger");
        logger.setUseParentHandlers(false); 

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); 
    }

    private LoggerUtil() {
        // Prevent instantiation
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }

    public static void debug(String message) {
        logger.fine(message);
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}
