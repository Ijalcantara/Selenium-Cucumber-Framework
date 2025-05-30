package StepDefinitions;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.ContactDetailPage;
import com.cheq.contactlist.pages.ContactListPage;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.MouseActionUtil;
import com.cheq.contactlist.utils.TestDataUtil;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactDetails {

	private WebDriver driver;
    private WaitUtil waitUtil;
    private MouseActionUtil mouseUtils;
    private LogInPage loginPage;
    ContactDetailPage contactDetail;
    ContactListPage contactlist;

    private String deletedContactIdentifier = null;

    
    public ContactDetails(Hooks hooks) throws IOException {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
        this.contactDetail = new ContactDetailPage(driver, hooks.getReporterUtil());
        this.contactlist = new ContactListPage(driver, waitUtil);
    }


    
    
    @When("user enter their credentials")
    public void user_enter_their_credentials(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        String email = row.get("email");
        String rawPassword = row.get("password");

        String resolvedPassword = TestDataUtil.resolvePassword(email, rawPassword, true);

        loginPage.enterEmail(email);
        loginPage.enterPassword(resolvedPassword);
    }

    @When("hit the login\\/submit button")
    public void hit_the_login_submit_button() {
        loginPage.clickSubmitButton();
    }

    @Then("user is on the home page")
    public void user_is_on_the_home_page() {
        
    }

    @When("user click one account in the list")
    public void user_click_one_account_in_the_list() {
    	deletedContactIdentifier = ElementAssertUtil.getFirstContactIdentifier(driver);
        contactlist.clickAnyContactRow();
    }

    @And("the user clicks the Delete Contact button")
    public void the_user_clicks_the_delete_contact_button() throws InterruptedException {
        contactDetail.clickDeleteContact();
        Thread.sleep(2);
    }

    @And("the user confirms deletion on the dialog box")
    public void the_user_confirms_deletion_on_the_dialog_box() throws IOException, AWTException {
    	ElementAssertUtil.confirmDeletion(driver, waitUtil);
    }

    @Then("the contact should be deleted from the Contact List and no longer visible")
    public void the_contact_should_be_deleted_from_the_contact_list_and_no_longer_visible() {
    }
}
