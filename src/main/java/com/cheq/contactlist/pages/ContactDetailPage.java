package com.cheq.contactlist.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.ReporterUtil;

public class ContactDetailPage {
	
	private WebDriver driver;
    private MouseActionUtil mouseUtils;


    public ContactDetailPage(WebDriver driver, ReporterUtil reporterUtil) throws IOException {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver, reporterUtil);
    }


    private By EDIT_CONTACT_BTN = By.id("edit-contact");
    private By DELETE_CONTACT_BTN = By.id("delete");
    private By RETURN_TO_LIST_BTN = By.id("return");
	    
    public void clickEditContact() {
    	mouseUtils.click(EDIT_CONTACT_BTN);
    }

    public void clickDeleteContact() {	        
    	mouseUtils.click(DELETE_CONTACT_BTN);
    }

    public void clickReturnToList() {
    	mouseUtils.click(RETURN_TO_LIST_BTN);
    }

 
}
