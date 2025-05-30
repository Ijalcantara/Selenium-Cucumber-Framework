package com.cheq.contactlist.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.ReporterUtil;
import com.cheq.contactlist.utils.ScreenshotUtil;

public class LogOutPage {

	protected WebDriver driver;
    protected MouseActionUtil mouseUtils;
    

    public LogOutPage(WebDriver driver) throws IOException {
    	this.driver = driver;

        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        ReporterUtil reporterUtil = new ReporterUtil(driver, screenshotUtil);

        this.mouseUtils = new MouseActionUtil(driver, reporterUtil);
    }
    
    private By LOGOUT_BTN = By.id("logout");
    
    public void clickLogOutButton() {
    	mouseUtils.click(LOGOUT_BTN);
    }
}
