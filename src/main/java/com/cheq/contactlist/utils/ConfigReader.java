package com.cheq.contactlist.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

//	private static final Properties properties = new Properties();
//    private static String env;
//
//    static {
//        loadProperties();
//    }
//
//    
//    private Properties properties;
//
//    public Properties initProperty() {
//        properties = new Properties();
//        try {
//            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
//            properties.load(fis);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties;
//    } 
//
//
//    /**
//     * Load configuration from the properties file.
//     */
//    private static void loadProperties() {
//        try {
//            String configPath = System.getProperty("config.path", "src/main/resources/config/Config.properties");
//            try (FileInputStream input = new FileInputStream(configPath)) {
//                properties.load(input);
//            }
//
//            // Determine environment
//            env = System.getProperty("env", properties.getProperty("ENV", "qa")).toLowerCase();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to load configuration file.");
//        }
//    }
//
//    /**
//     * Reload properties during runtime if needed.
//     */
//    public static void reload() {
//        properties.clear();
//        loadProperties();
//    }
//
//    /**
//     * Get the raw property value (no environment prefix).
//     */
//    public static String get(String key) {
//        return System.getProperty(key, properties.getProperty(key));
//    }
//
//    /**
//     * Get an environment-specific property, e.g. baseUrl.qa or timeout.dev
//     */
//    public static String getEnvSpecific(String key) {
//        return System.getProperty(key, properties.getProperty(key + "." + env));
//    }
//
//
//    public static String getEnv() {
//        return env;
//    }
//
//    /**
//     * Check if a property key exists.
//     */
//    public static boolean containsKey(String key) {
//        return properties.containsKey(key);
//    }
	
	 private static final Properties properties = new Properties(); // ✅ static shared instance
	    private static String env;

	    static {
	        loadProperties();
	    }

	    /**
	     * Load configuration from the properties file.
	     */
	    private static void loadProperties() {
	        try {
	            String configPath = System.getProperty("config.path", "src/main/resources/config/Config.properties");
	            try (FileInputStream input = new FileInputStream(configPath)) {
	                properties.load(input);
	            }

	            env = System.getProperty("env", properties.getProperty("ENV", "qa")).toLowerCase();
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load configuration file.");
	        }
	    }

	    public static void reload() {
	        properties.clear();
	        loadProperties();
	    }

	    public static String get(String key) {
	        return System.getProperty(key, properties.getProperty(key));
	    }

	    public static String getEnvSpecific(String key) {
	        return System.getProperty(key, properties.getProperty(key + "." + env));
	    }

	    public static String getEnv() {
	        return env;
	    }

	    public static boolean containsKey(String key) {
	        return properties.containsKey(key);
	    }

	    /** Optional: expose raw properties if needed **/
	    public static Properties getProperties() {
	        return properties;
	    }
}
