package com.cheq.contactlist.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    
    public void clickAnyContactRow() {
        //By rowLocator = By.cssSelector("table tbody tr");
        By rowLocator = By.className("contactTableBodyRow");

        List<WebElement> rows = driver.findElements(rowLocator);

        if (rows.isEmpty()) {
            throw new RuntimeException("No contact rows found in the contact list table.");
        }
        rows.get(0).click();

    }
}
