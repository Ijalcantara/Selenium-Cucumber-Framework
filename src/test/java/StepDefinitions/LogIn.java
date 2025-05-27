package StepDefinitions;


import java.awt.AWTException;
import java.io.IOException;


import org.openqa.selenium.WebDriver;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.TestDataResolver;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogIn {

	private WebDriver driver;
    private WaitUtil waitUtil;
    private LogInPage loginPage;
    private String currentEmail;

    public LogIn(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver); 
    }

    @Given("browser is open")
    public void browser_is_open() {
    }

    @And("user is on landing\\/log in page")
    public void user_is_on_landing_log_in_page() throws IOException, AWTException {
        loginPage.verifyEmailFieldPresent();
        loginPage.verifyPasswordFieldPresent();
        loginPage.verifySubmitButtonIsEnabled();
        loginPage.verifySignUpButtonVisible();
    }
    
    @When("user enter a valid email {string}")
    public void user_enter_a_valid_email_address(String email) {
        this.currentEmail = email;
        loginPage.enterEmail(email);
    }

    @When("user enter a invalid email {string}")
    public void user_enter_a_invalid_email(String email) {
        this.currentEmail = email;
        loginPage.enterEmail(email);
    }

    @And("user enter a valid password {string}")
    public void user_enter_a_valid_password(String password) {
    	String resolvedPassword = TestDataResolver.resolvePassword(currentEmail, password, true);
        loginPage.enterPassword(resolvedPassword);
    }

    @And("user enter a invalid password {string}")
    public void user_enter_an_invalid_password(String password) {
    	String resolvedPassword = TestDataResolver.resolvePassword(currentEmail, password, false);
        loginPage.enterPassword(resolvedPassword);
    }

    @And("user leave an empty password {string}")
    public void user_leave_an_empty_password(String password) {
    	String resolvedPassword = TestDataResolver.resolvePassword(currentEmail, password, false);
        loginPage.enterPassword(resolvedPassword);
    }

    @And("hit Submit")
    public void hit_submit() {
        loginPage.clickSubmitButton();
    }

    @Then("user is navigate to Contact List Page")
    public void user_is_navigate_to_contact_list_page() throws IOException, AWTException {
        waitUtil.waitForUrlToContain("/contactList");
    }

    @Then("click logout")
    public void click_logout() {
        loginPage.clickLogOutButton();
    }

    
    @Then("the login fails and display an error message {string}")
    public void the_login_fails_and_display_an_error_message(String expectedMessage) {
    	loginPage.verifyErrorMessageDisplayed(expectedMessage);
    }
}
