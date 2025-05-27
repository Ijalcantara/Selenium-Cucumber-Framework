package com.cheq.contactlist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.WaitUtil;

public class ContactListPage {

	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;
    private WaitUtil waitUtil;
    		
    public ContactListPage(WebDriver driver, WaitUtil waitUtil) {
        this.driver = driver;
        this.waitUtil = waitUtil;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver); 
    }

    private final By ADD_CONTACT_BTN = By.id("add-contact");


    public void clickAddContact() {
        mouseUtils.click(ADD_CONTACT_BTN);
    }
  
    public void clickAnyContactRow() {
    	mouseUtils.clickAnyContactRow();
    }
}
