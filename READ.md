# Cucumber Selenium Java Automation Framework

This repository contains a robust and modular Cucumber-Selenium Java test automation framework for testing the [Thinking Tester Contact List](https://thinking-tester-contact-list.herokuapp.com/) web application.

## 🚀 Features

- **BDD with Cucumber** – Step definitions mapped to human-readable `.feature` files.
- **Selenium WebDriver** – Automates user interactions with web UI.
- **WebDriverManager** – Manages driver binaries automatically.
- **JUnit/TestNG Support** – Can be configured for either.
- **Hooks for Test Lifecycle** – Includes screenshot capture after each step.
- **Page Object Model (POM)** – Clean abstraction for maintainable test code.
- **Logging** – Integrated logging using `LoggerUtil`.
- **Screenshot Utility** – Captures and stores screenshots on step failure.
- **Reporter Utility** – Logs pass/fail and attaches screenshots as needed.
- **Soft Assertions** – Allows continued execution and reporting of all failures.
- **Configurable Execution** – Read from `config.properties`.

## 📁 Project Structure

src
├── main
│ └── java
│ ├── config # ConfigReader and setup utils
│ ├── drivers # WebDriver factory
│ ├── hooks # Cucumber Hooks for setup/teardown
│ ├── pages # Page Object classes
│ ├── utils # LoggerUtil, ReporterUtil, ScreenshotUtil, etc.
├── test
│ └── java
│ ├── stepdefinitions # Step definition implementations
│ └── runners # Test runners using CucumberOptions
└── resources
├── features # .feature files
└── config.properties # Environment-specific configs