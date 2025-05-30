package StepDefinitions;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.ContactDetailPage;
import com.cheq.contactlist.pages.ContactListPage;
import com.cheq.contactlist.pages.EditContactPage;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.pages.LogOutPage;
import com.cheq.contactlist.utils.TestDataUtil;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditContact {

	private WebDriver driver;
    private WaitUtil waitUtil;
    private LogInPage loginPage;
    private ContactListPage contactListPage;
    private EditContactPage editContactPage;
    private ContactDetailPage contactDetail;
    private LogOutPage logout;
    
    public EditContact(Hooks hooks) throws IOException {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
        this.contactListPage = new ContactListPage(driver, waitUtil);
        this.editContactPage = new EditContactPage(driver, hooks.getReporterUtil()); 
        this.contactDetail = new ContactDetailPage(driver, hooks.getReporterUtil());
        this.logout = new LogOutPage(driver);
    }

    @When("user enter required valid credentials")
    public void user_enter_required_valid_credentials(DataTable dataTable) {
    	Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        String email = row.get("email");
        String rawPassword = row.get("password");

        String resolvedPassword = TestDataUtil.resolvePassword(email, rawPassword, true);

        loginPage.enterEmail(email);
        loginPage.enterPassword(resolvedPassword);
    }

    @And("click the editContact button")
    public void click_the_edit_contact_button() {
        loginPage.clickSubmitButton();
    }

    @Then("user can see all of the contact list")
    public void user_can_see_all_of_the_contact_list() {
    }

    @When("user click one contact in the table")
    public void user_click_one_contact_in_the_table() {
        contactListPage.clickAnyContactRow(); 
    }

    @And("hit EditContact button")
    public void hit_edit_contact_button() {
        contactDetail.clickEditContact(); 
    }

    @Then("user can edit details of the chosen contact")
    public void user_can_edit_details_of_the_chosen_contact() {
    }

    @When("user enter new name")
    public void user_enter_new_name(DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        editContactPage.enterFirstName(row.get("firstName"));
        editContactPage.enterLastName(row.get("lastName"));
        editContactPage.enterCountry(row.get("country"));
    }

    @When("hit the ediContact submit button")
    public void hit_the_edi_contact_submit_button() {
        editContactPage.clickSubmit();
    }

    @Then("user successfully edit the contact")
    public void user_successfully_edit_the_contact() { 
    }
    
    @Then("hit Return to Contact button")
    public void hit_return_to_contact_button() {
    	contactDetail.clickReturnToList();
    }
    
    @Then("user will navigated to the contactList page")
    public void user_will_navigated_to_the_contact_list_page() {
    }
    
    @When("hit logout button")
    public void hit_logout_button() {
    	logout.clickLogOutButton();
    }

    @Then("user is redirected to login page")
    public void user_is_redirected_to_login_page() {
       
    }
}

