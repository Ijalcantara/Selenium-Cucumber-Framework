package com.cheq.contactlist.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.WaitUtil;

public class ContactDetailPage {
	
	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;
    private WaitUtil waitUtil;

    // Constructor for Add Contact Page
    public ContactDetailPage(WebDriver driver, WaitUtil waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver, 10);
    }


    private By EDIT_CONTACT_BTN = By.id("edit-contact");
    private By DELETE_CONTACT_BTN = By.id("delete");
    private By RETURN_TO_LIST_BTN = By.id("return");
    
    private By FIRSTNAME_TXT = By.id("firstName");
    private By LASTNAME_TXT = By.id("lastName");
    private By DOB_TXT = By.id("birthDate");
    private By EMAIL_TXT = By.id("email");
    private By PHONE_TXT = By.id("phone");
    private By STREET1_TXT = By.id("street1");
    private By STREET2_TXT = By.id("street2");
    private By CITY_TXT = By.id("city");
    private By STATE_TXT = By.id("stateProvince");
    private By POSTALCODE_TXT = By.id("postalCode");
    private By COUNTRY_TXT = By.id("country");

	    
    public void clickEditContact() {
    	mouseUtils.click(EDIT_CONTACT_BTN);
    }

    public void clickDeleteContact() {	        
    	mouseUtils.click(DELETE_CONTACT_BTN);
    }

    public void clickReturnToList() {
    	mouseUtils.click(RETURN_TO_LIST_BTN);
    }

    public String getFirstName() {
        return getTextWithoutLabel(driver.findElement(FIRSTNAME_TXT), "First Name:");
    }

    public String getLastName() {
        return getTextWithoutLabel(driver.findElement(LASTNAME_TXT), "Last Name:");
    }

    public String getDateOfBirth() {
        return getTextWithoutLabel(driver.findElement(DOB_TXT), "Date of Birth:");
    }

    public String getEmail() {
        return getTextWithoutLabel(driver.findElement(EMAIL_TXT), "Email:");
    }

    public String getPhone() {
        return getTextWithoutLabel(driver.findElement(PHONE_TXT), "Phone:");
    }

    public String getStreetAddress1() {
        return getTextWithoutLabel(driver.findElement(STREET1_TXT), "Street Address 1:");
    }

    public String getStreetAddress2() {
        return getTextWithoutLabel(driver.findElement(STREET2_TXT), "Street Address 2:");
    }

    public String getCity() {
        return getTextWithoutLabel(driver.findElement(CITY_TXT), "City:");
    }

    public String getState() {
        return getTextWithoutLabel(driver.findElement(STATE_TXT), "State or Province:");
    }

    public String getPostalCode() {
        return getTextWithoutLabel(driver.findElement(POSTALCODE_TXT), "Postal Code:");
    }

    public String getCountry() {
        return getTextWithoutLabel(driver.findElement(COUNTRY_TXT), "Country:");
    }

    private String getTextWithoutLabel(WebElement element, String label) {
        return element.getText().replace(label, "").trim();
    }
    
    public void assertDeleteConfirmationPromptIsDisplayed() {
        Alert alert = waitUtil.waitForAlert();
        Assert.assertNotNull(alert, "Delete confirmation alert did not appear");
 
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
 
        String alertText = alert.getText();
        Assert.assertTrue(alertText.toLowerCase().contains("delete"),
            "Alert text does not mention deletion: " + alertText);
    }
    
    public void confirmDeleteOnConfirmationDialog() {
        Alert alert = waitUtil.waitForAlert();
        Assert.assertNotNull(alert, "Delete confirmation alert did not appear");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        alert.accept();
    }
}
