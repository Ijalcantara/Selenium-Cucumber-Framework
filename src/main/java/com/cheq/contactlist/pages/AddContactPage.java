package com.cheq.contactlist.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;

public class AddContactPage {

	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;

    public AddContactPage(WebDriver driver) throws IOException {
    	this.driver = driver;
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver); 
        ReporterUtil reporterUtil = new ReporterUtil(driver, screenshotUtil);
        this.mouseUtils = new MouseActionUtil(driver, reporterUtil);
        this.keyboardUtils = new KeyboardActionUtil(driver, screenshotUtil);
    }

    private By FIRST_NAME = By.id("firstName");
    private By LAST_NAME = By.id("lastName");
    private By DATE_OF_BIRTH = By.id("birthdate");
    private By EMAIL = By.id("email");
    private By PHONE = By.id("phone");
    private By STREET_ADDRESS1 = By.id("street1");
    private By STREET_ADDRESS2 = By.id("street2");
    private By CITY = By.id("city");
    private By STATE = By.id("stateProvince");
    private By POSTAL_CODE = By.id("postalCode");
    private By COUNTRY = By.id("country");
    private By SUBMIT_BUTTON = By.id("submit");
    private By CANCEL_BUTTON = By.id("cancel");

    public void enterFirstName(String firstName) {
    	keyboardUtils.typeText(FIRST_NAME, firstName, true);
    }

    public void enterLastName(String lastName) {
    	keyboardUtils.typeText(LAST_NAME, lastName, true);
    }

    public void enterBirthdate(String birthdate) {
    	keyboardUtils.typeText(DATE_OF_BIRTH, birthdate, true);
    }

    public void enterEmail(String email) {
    	keyboardUtils.typeText(EMAIL, email, true);
    }

    public void enterPhone(String phone) {
    	keyboardUtils.typeText(PHONE, phone, true);
    }

    public void enterStreet1(String street1) {    	
    	keyboardUtils.typeText(STREET_ADDRESS1, street1, true);
    }

    public void enterStreet2(String street2) {   	
    	keyboardUtils.typeText(STREET_ADDRESS2, street2, true);
    }

    public void enterCity(String city) {    	 
    	keyboardUtils.typeText(CITY, city, true);
    }

    public void enterState(String state) { 	
    	keyboardUtils.typeText(STATE, state, true);
    }

    public void enterPostalCode(String postalCode) {
    	keyboardUtils.typeText(POSTAL_CODE, postalCode, true);
    }

    public void enterCountry(String country) {
    	keyboardUtils.typeText(COUNTRY, country, true);
    }

    public void clickSubmitButton() {
        mouseUtils.click(SUBMIT_BUTTON);
    }

    public void clickCancelButton() {
        mouseUtils.click(CANCEL_BUTTON);
    }
    
    public void verifyErrorMessageDisplayed(String expectedText) throws Exception {
    	ElementAssertUtil.assertErrorMessageContains(driver, expectedText);
    }  
}
