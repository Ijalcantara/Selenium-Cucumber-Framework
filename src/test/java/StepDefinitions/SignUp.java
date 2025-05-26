package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.pages.SignUpPage;
import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUp {

	private WebDriver driver;
	private WaitUtil waitUtil;
    private SignUpPage signupPage;
    private LogInPage loginPage;

    public SignUp(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.signupPage = new SignUpPage(driver);
        this.loginPage = new LogInPage(driver);
    }

    @Given("user hit Sign up")
    public void user_hit_sign_up() {
        loginPage.clickSignUpButton();
    }

    @Then("user is on addUser page")
    public void user_is_on_add_user_page() {
        String currentUrl = driver.getCurrentUrl();
        
    }
    
    @When("user leave all fields empty")
	public void user_leave_all_fields_empty() {
    	signupPage.enterFirstName("");
        signupPage.enterLastName("");
        signupPage.enterEmail("");
        signupPage.enterPassword("");
	}

    @When("user input in all fields")
    public void user_input_in_all_fields(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            signupPage.enterFirstName(row.get("firstName"));
            signupPage.enterLastName(row.get("lastName"));
            signupPage.enterEmail(row.get("email"));
            signupPage.enterPassword(row.get("password"));
        }
    }
  
    @And("hit the signUp Submit button")
    public void hit_the_sign_up_submit_button() {
    	signupPage.clickSubmitButton();
    	waitUtil.sleep(2);
    }  
    
    @Then("new user is successfully added and redirected to Contact List")
    public void new_user_is_successfully_added_and_redirected_to_contact_list() {
    	boolean redirected = waitUtil.waitForUrlToContain("/contactList");
    }

    @Then("user is not added and an error message is displayed")
    public void user_is_not_added_and_an_error_message_is_displayed() {
    	waitUtil.sleep(1000); 
        String error = signupPage.getErrorMessageText();
        signupPage.verifyErrorMessageDisplayed(error);
    }

    @Then("the form should not be submitted")
    public void the_form_should_not_be_submitted() {
        boolean stillOnAddUser = waitUtil.waitForUrlToContain("/addUser");
    }
}
