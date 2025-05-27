package com.cheq.contactlist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.WaitUtil;

public class EditContactPage {

	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;
    
    public EditContactPage(WebDriver driver) {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver); 
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

    public void enterDateOfBirth(String dateOfBirth) {
    	keyboardUtils.typeText(DATE_OF_BIRTH, dateOfBirth, true);
    }

    public void enterEmail(String email) {
    	keyboardUtils.typeText(EMAIL, email, true);
    }

    public void enterPhone(String phone) {
    	keyboardUtils.typeText(PHONE, phone, true);
    }

    public void enterStreetAddress1(String streetAddress1) {
    	keyboardUtils.typeText(STREET_ADDRESS1, streetAddress1, true);
    }

    public void enterStreetAddress2(String streetAddress2) {
    	keyboardUtils.typeText(STREET_ADDRESS2, streetAddress2, true);
    }

    public void enterCity(String city) {
    	keyboardUtils.typeText(CITY, city, true);
    }

    public void enterStateProvince(String stateProvince) {
    	keyboardUtils.typeText(STATE, stateProvince, true);
    }

    public void enterPostalCode(String postalCode) {
    	keyboardUtils.typeText(POSTAL_CODE, postalCode, true);
    }

    public void enterCountry(String country) {
    	keyboardUtils.typeText(COUNTRY, country, true);
    }

    public void clickSubmit() {
        mouseUtils.click(SUBMIT_BUTTON);
    }
 
    public void clickCancel() {
        mouseUtils.click(CANCEL_BUTTON);
    }
}
