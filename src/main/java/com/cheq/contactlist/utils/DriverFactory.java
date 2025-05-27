package com.cheq.contactlist.utils;

import java.time.Duration;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
//	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//    // Config keys
//    private static final String BROWSER_KEY = "browser";
//    private static final String IMPLICIT_WAIT_KEY = "implicit_wait";
//    private static final String CHROME_PATH_KEY = "CHROME_PATH";
//    private static final String FIREFOX_PATH_KEY = "FIREFOX_PATH";
//    private static final String EDGE_PATH_KEY = "EDGE_PATH";
//
//    /**
//     * Initializes the WebDriver based on configuration.
//     */
//    public static WebDriver initDriver() {
//        String browser = Optional.ofNullable(ConfigReader.get(BROWSER_KEY))
//                .orElseThrow(() -> new IllegalArgumentException("Browser type not specified in config"));
//        long waitSeconds = Long.parseLong(Optional.ofNullable(ConfigReader.get(IMPLICIT_WAIT_KEY)).orElse("10"));
//
//        tlDriver.set(createDriver(browser.toLowerCase(Locale.ROOT)));
//
//        WebDriver driver = getDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitSeconds));
//        return driver;
//    }
//
//    /**
//     * Returns the current WebDriver instance from ThreadLocal.
//     */
//    public static WebDriver getDriver() {
//        return tlDriver.get();
//    }
//
//    /**
//     * Quits and cleans up the WebDriver instance.
//     */
//    public static void quitDriver() {
//        WebDriver driver = tlDriver.get();
//        if (driver != null) {
//            driver.quit();
//            tlDriver.remove();
//        }
//    }
//
//    /**
//     * Creates a WebDriver instance based on browser type.
//     */
//    private static WebDriver createDriver(String browser) {
//        switch (browser) {
//            case "chrome":
//                System.setProperty("webdriver.chrome.driver", ConfigReader.get(CHROME_PATH_KEY));
//                return new ChromeDriver();
//            case "firefox":
//                System.setProperty("webdriver.gecko.driver", ConfigReader.get(FIREFOX_PATH_KEY));
//                return new FirefoxDriver();
//            case "edge":
//                System.setProperty("webdriver.edge.driver", ConfigReader.get(EDGE_PATH_KEY));
//                return new EdgeDriver();
//            default:
//                throw new IllegalArgumentException("Unsupported browser: " + browser);
//        }
//    }
	
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser");
        String implicitWait = ConfigReader.get("implicit_wait");

        switch (Objects.requireNonNull(browser).toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", ConfigReader.get("CHROME_PATH"));
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", ConfigReader.get("FIREFOX_PATH"));
                tlDriver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", ConfigReader.get("EDGE_PATH"));
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
