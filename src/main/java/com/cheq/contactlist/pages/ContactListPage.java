package com.cheq.contactlist.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.utils.KeyboardActionUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;
import com.cheq.contactlist.utils.WaitUtil;

public class ContactListPage {

	private WebDriver driver;
    private MouseActionUtil mouseUtils;
    private KeyboardActionUtil keyboardUtils;
    private WaitUtil waitUtil;
    		
    public ContactListPage(WebDriver driver, WaitUtil waitUtil) throws IOException {
        this.driver = driver;
        this.waitUtil = waitUtil;
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        ReporterUtil reporterUtil = new ReporterUtil(driver, screenshotUtil);
        this.mouseUtils = new MouseActionUtil(driver, reporterUtil);
        this.keyboardUtils = new KeyboardActionUtil(driver, screenshotUtil);
    }

    private final By ADD_CONTACT_BTN = By.id("add-contact");


    public void clickAddContact() {
        mouseUtils.click(ADD_CONTACT_BTN);
    }
  
    public void clickAnyContactRow() {
    	mouseUtils.clickAnyContactRow();
    }
}
