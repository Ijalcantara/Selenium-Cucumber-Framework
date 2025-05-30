package com.cheq.contactlist.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;

public class LogInPage {
	
	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;

    private By EMAIL_INPUT = By.id("email");
    private By PASSWORD_INPUT = By.id("password");
    private By SUBMIT_BUTTON = By.id("submit");
    private By LOGOUT_BUTTON = By.id("logout");
    private By SIGNUP_BUTTON = By.id("signup");

    
    public LogInPage(WebDriver driver) throws IOException {
        this.driver = driver;

        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        ReporterUtil reporterUtil = new ReporterUtil(driver, screenshotUtil);

        this.mouseUtils = new MouseActionUtil(driver, reporterUtil);
        this.keyboardUtils = new KeyboardActionUtil(driver, screenshotUtil);
    }

    public void enterEmail(String email) {
    	keyboardUtils.typeText(EMAIL_INPUT, email, true);
    }

    public void enterPassword(String password) {
    	keyboardUtils.typeText(PASSWORD_INPUT, password, true);
    }

    public void enterPasswordInCaps(String password) {
    	keyboardUtils.typeText(PASSWORD_INPUT, password, true);
    }

    public void clickSubmitUsingEnterKey() {
        keyboardUtils.pressEnter();
    }

    public void clickSubmitButton() {
        mouseUtils.click(SUBMIT_BUTTON);
    }
    
    public void clickSignUpButton() {
        mouseUtils.click(SIGNUP_BUTTON);
    }

    public void clickLogOutButton() {
        mouseUtils.click(LOGOUT_BUTTON);
    }

    public void verifySubmitButtonIsEnabled() {
        ElementAssertUtil.assertSubmitButtonEnabled(driver, SUBMIT_BUTTON);
    }

    public void verifyEmailFieldPresent() {
    	ElementAssertUtil.assertEmailFieldDisplayed(driver, EMAIL_INPUT);
    }

    public void verifyPasswordFieldPresent() {
    	ElementAssertUtil.assertPasswordFieldDisplayed(driver, PASSWORD_INPUT);
    }

    public void verifySignUpButtonVisible() {
        ElementAssertUtil.assertSignupButtonEnabled(driver, SIGNUP_BUTTON);
    }
    
    public void verifyErrorMessageDisplayed(String expectedText) throws Exception {
        ElementAssertUtil.assertErrorMessageContains(driver, expectedText);
    }
}
