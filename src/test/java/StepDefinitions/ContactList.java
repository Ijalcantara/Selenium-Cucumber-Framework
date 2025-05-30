package StepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.TestDataUtil;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactList {

	private WebDriver driver;
	private WaitUtil waitUtil;
    private LogInPage loginPage;

    public ContactList(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
    }
    
	@Given("user is in login page")
	public void user_is_in_login_page() {
	}

	@When("user input a valid credentials")
	public void user_input_a_valid_credentials(DataTable dataTable) {
		Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        String email = row.get("email");
        String rawPassword = row.get("password");

        String resolvedPassword = TestDataUtil.resolvePassword(email, rawPassword, true);

        loginPage.enterEmail(email);
        loginPage.enterPassword(resolvedPassword);
        }

	@When("hit the Submit button")
	public void hit_the_submit_button() {
		loginPage.clickSubmitButton();
	}

	@Then("user is redirected to contact details page.")
	public void user_is_redirected_to_contact_details_page() {
		boolean redirected = waitUtil.waitForUrlToContain("/contactList");
	}
}
