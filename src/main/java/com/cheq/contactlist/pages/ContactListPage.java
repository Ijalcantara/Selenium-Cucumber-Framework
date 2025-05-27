package com.cheq.contactlist.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cheq.contactlist.utils.ElementAssertUtil;
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
        this.keyboardUtils = new KeyboardActionUtil(driver, 10); 
    }

    private final By ADD_CONTACT_BTN = By.id("add-contact");


    public void clickAddContact() {
        mouseUtils.click(ADD_CONTACT_BTN);
    }
  
    public void clickAnyContactRow() {
        By contactRowLocator = By.cssSelector(".contactTableBodyRow");
        WebElement contactRow = waitUtil.waitForElementToBeVisible(contactRowLocator);
        contactRow.click();
    }
    
    // Methods for deletion of contact
    public void assertContactIsDeleted(String identifier) {
        List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));

        boolean contactStillPresent = rows.stream()
            .anyMatch(row -> row.getText().toLowerCase().contains(identifier.toLowerCase()));

        ElementAssertUtil.assertFalse(contactStillPresent, "Contact still present but should be deleted: " + identifier);
    }

    public boolean isContactPresentInList() {
        try {
            List<WebElement> contactRows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
            return !contactRows.isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }  
      
    public String getFirstContactIdentifier() {
        List<WebElement> rows = driver.findElements(By.cssSelector(".contactTableBodyRow"));
        if (!rows.isEmpty()) {
            return rows.get(0).getText(); 
        }
        return "";
    }
}
