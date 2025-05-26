package com.cheq.contactlist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cheq.contactlist.utils.MouseActionUtil;

public class BasePage {

	protected WebDriver driver;
    protected MouseActionUtil mouseUtils;
    

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.mouseUtils = new MouseActionUtil(driver);
    }
    
    private By LOGOUT_BTN = By.id("logout");
    
    public void clickLogOutButton() {
    	mouseUtils.click(LOGOUT_BTN);
    }
}
