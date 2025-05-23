package com.cheq.contactlist.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;

public class ContactListPage {

	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;

    public ContactListPage(WebDriver driver) {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver);
        this.keyboardUtils = new KeyboardActionUtil(driver, 10); // Assuming 10 seconds timeout
    }


    private final By ADD_CONTACT_BTN = By.id("add-contact");

    private By getContactCardByName(String fullName) {
        return By.xpath("//div[contains(@class,'contact')][.//span[contains(text(),'" + fullName + "')]]");
    }


    public void clickAddContact() {
        mouseUtils.click(ADD_CONTACT_BTN);
    }
}
