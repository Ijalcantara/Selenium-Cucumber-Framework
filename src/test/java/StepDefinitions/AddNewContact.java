package StepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.AddContactPage;
import com.cheq.contactlist.pages.ContactListPage;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.TestDataResolver;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewContact {

	private WebDriver driver;
	private WaitUtil waitUtil;
    private LogInPage loginPage;
    private ContactListPage contactListPage;
    private AddContactPage addContact;

    public AddNewContact(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
        this.contactListPage = new ContactListPage(driver, waitUtil);
        this.addContact = new AddContactPage(driver);
    }
    
	@Given("user is already in login page")
	public void user_is_already_in_login_page() {	    
	}

	@When("user input a credentials to open an account")
	public void user_input_a_credentials_to_open_an_account(DataTable dataTable) {
		Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        String email = row.get("email");
        String rawPassword = row.get("password");

        String resolvedPassword = TestDataResolver.resolvePassword(email, rawPassword, true);

        loginPage.enterEmail(email);
        loginPage.enterPassword(resolvedPassword);
	}

	@Then("user is navigated in the ContactList page")
	public void user_is_navigated_in_the_contact_list_page() {
		waitUtil.pause(2);
		boolean redirected = waitUtil.waitForUrlToContain("/contactList");
	}

	@When("hit the addNewContact button")
	public void hit_the_add_new_contact_button() {
		contactListPage.clickAddContact();
	}

	@When("user enter details in all fields")
	public void user_enter_details_in_all_fields(DataTable dataTable) {
		Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        addContact.enterFirstName(row.get("firstName"));
        addContact.enterLastName(row.get("lastName"));
        addContact.enterBirthdate(row.get("birthdate"));
        addContact.enterEmail(row.get("email"));
        addContact.enterPhone(row.get("phone"));
        addContact.enterStreet1(row.get("street1"));
        addContact.enterStreet2(row.get("street2"));
        addContact.enterCity(row.get("city"));
        addContact.enterState(row.get("stateProvince"));
        addContact.enterPostalCode(row.get("postalCode"));
        addContact.enterCountry(row.get("country"));              
        }
	
	@When("click addnewContact submit button")
	public void click_addnew_contact_submit_button() {
		addContact.clickSubmitButton();
	}
	
	@Then("new user is added in the contact list page")
	public void new_user_is_added_in_the_contact_list_page() {
		boolean redirected = waitUtil.waitForUrlToContain("/contactList");
	}
	
	@Then("error occured and display error message {string}")
	public void error_occured_and_display_error_message(String expectedMessage) {
	    addContact.verifyErrorMessageDisplayed(expectedMessage); 
	}
}
