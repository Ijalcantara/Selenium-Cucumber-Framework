package com.cheq.contactlist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;

public class SignUpPage {
	
	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver);
    }

    private By FIRSTNAME_INPUT = By.id("firstName");
    private By LASTNAME_INPUT = By.id("lastName");
    private By EMAIL_INPUT = By.id("email");
    private By PASSWORD_INPUT = By.id("password");
    private By SUBMIT_BTN = By.id("submit");
    private By CANCEL_BTN = By.id("cancel");

    public void enterFirstName(String firstName) {
    	keyboardUtils.typeText(FIRSTNAME_INPUT, firstName, true);
    }

    public void enterFirstNameinCaps(String firstName) {
        keyboardUtils.typeWithShift(FIRSTNAME_INPUT, firstName);
    }
    
    public void enterLastName(String lastName) {
        keyboardUtils.typeText(LASTNAME_INPUT, lastName, true);
    }
    
    public void enterLastNameinCaps(String lastName) {
        keyboardUtils.typeWithShift(LASTNAME_INPUT, lastName);
    }

    public void enterEmail(String email) {
        keyboardUtils.typeText(EMAIL_INPUT, email, true);
    }

    public void enterPassword(String password) {
        keyboardUtils.typeText(PASSWORD_INPUT, password, true);
    }

    public void clickSubmitButton() {
        mouseUtils.click(SUBMIT_BTN);
    }

    public void clickCancelButton() {
        mouseUtils.click(CANCEL_BTN);
    }
    
    public void verifyErrorMessageDisplayed(String expectedText) {
    	ElementAssertUtil.assertErrorMessageContains(driver, expectedText);
    }
}
