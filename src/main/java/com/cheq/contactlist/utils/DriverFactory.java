package com.cheq.contactlist.utils;

import java.time.Duration;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * DriverFactory is a utility class responsible for creating and managing
 * WebDriver instances using the ThreadLocal pattern for thread safety.
 * <p>
 * It reads configuration from the {@link ConfigReader} to decide the browser type
 * and implicit wait duration. This allows parallel test execution with isolated driver instances.
 */
public class DriverFactory {

//    /**
//     * Thread-local storage for WebDriver instances.
//     * Ensures each thread has its own WebDriver to prevent test collisions.
//     */
//    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    /**
//     * Initializes the WebDriver based on the configured browser name and wait time.
//     * Supports Chrome, Firefox, and Edge browsers.
//     *
//     * @return Initialized {@link WebDriver} instance
//     * @throws IllegalArgumentException if the browser type is unsupported
//     */
//    public static WebDriver initDriver() {
//        String browser = ConfigReader.get("browser");
//        String implicitWait = ConfigReader.get("implicit_wait");
//
//        switch (Objects.requireNonNull(browser).toLowerCase()) {
//            case "chrome":
//                System.setProperty("webdriver.chrome.driver", ConfigReader.get("CHROME_PATH"));
//                tlDriver.set(new ChromeDriver());
//                break;
//            case "firefox":
//                System.setProperty("webdriver.gecko.driver", ConfigReader.get("FIREFOX_PATH"));
//                tlDriver.set(new FirefoxDriver());
//                break;
//            case "edge":
//                System.setProperty("webdriver.edge.driver", ConfigReader.get("EDGE_PATH"));
//                tlDriver.set(new EdgeDriver());
//                break;
//            default:
//                throw new IllegalArgumentException("Browser not supported: " + browser);
//        }
//
//        getDriver().manage().window().maximize();
//        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
//        return getDriver();
//    }
//
//    /**
//     * Returns the current thread's WebDriver instance.
//     *
//     * @return The {@link WebDriver} associated with the current thread
//     */
//    public static WebDriver getDriver() {
//        return tlDriver.get();
//    }
//
//    /**
//     * Quits the WebDriver instance for the current thread and removes it from ThreadLocal storage.
//     * This is critical for preventing memory leaks in parallel executions.
//     */
//    public static void quitDriver() {
//        if (tlDriver.get() != null) {
//            tlDriver.get().quit();
//            tlDriver.remove();
//        }
//    }
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser");
        String implicitWait = ConfigReader.get("implicit_wait");

        switch (Objects.requireNonNull(browser).toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicitWait)));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}