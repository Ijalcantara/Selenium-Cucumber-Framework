package com.cheq.contactlist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.WaitUtil;

public class LogInPage {
	
	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;

    // Locators
    private By EMAIL_INPUT = By.id("email");
    private By PASSWORD_INPUT = By.id("password");
    private By SUBMIT_BUTTON = By.id("submit");
    private By ERROR_MESSAGE = By.id("error");
    private By LOGOUT_BUTTON = By.id("logout");
    private By SIGNUP_BUTTON = By.id("signup");

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver, 10); // Assuming 10 seconds timeout
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
        WebElement logoutButton = driver.findElement(LOGOUT_BUTTON);
        logoutButton.click();
    }

    public WebElement getErrorMessageElement() {
        return driver.findElement(ERROR_MESSAGE);
    }
    
    public By getErrorMessageLocator() {
        return ERROR_MESSAGE;
    }

    public String getErrorMessageText() {
        try {
            WebElement errorElement = driver.findElement(ERROR_MESSAGE);
            return errorElement.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }
}
