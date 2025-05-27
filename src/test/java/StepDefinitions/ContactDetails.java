package StepDefinitions;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.ContactDetailPage;
import com.cheq.contactlist.pages.ContactListPage;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.TestDataResolver;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactDetails {

	private WebDriver driver;
    private WaitUtil waitUtil;
    private LogInPage loginPage;
    private ContactDetailPage contactDetail;
    private ContactListPage contactlist;
    private String deletedContactIdentifier;

    public ContactDetails(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
        this.contactDetail = new ContactDetailPage(driver, waitUtil);
        this.contactlist = new ContactListPage(driver, waitUtil);
    }

    @When("user enter their credentials")
    public void user_enter_their_credentials(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> row = dataTable.asMaps(String.class, String.class).get(0);
        String email = row.get("email");
        String rawPassword = row.get("password");

        String resolvedPassword = TestDataResolver.resolvePassword(email, rawPassword, true);

        loginPage.enterEmail(email);
        loginPage.enterPassword(resolvedPassword);
    }

    @When("hit the login\\/submit button")
    public void hit_the_login_submit_button() {
        loginPage.clickSubmitButton();
    }

    @Then("user is on the home page")
    public void user_is_on_the_home_page() {
        waitUtil.pause(2);
        waitUtil.waitForUrlToContain("/contactList");
    }

    @When("user click one account in the list")
    public void user_click_one_account_in_the_list() {
    	deletedContactIdentifier = ElementAssertUtil.getFirstContactIdentifier(driver);
        contactlist.clickAnyContactRow();
    }

    @And("the user clicks the Delete Contact button")
    public void the_user_clicks_the_delete_contact_button() {
        contactDetail.clickDeleteContact();
    }

    @And("the user confirms deletion on the dialog box")
    public void the_user_confirms_deletion_on_the_dialog_box() {
        Alert alert = ElementAssertUtil.assertAlertIsPresent(driver, waitUtil, "delete");
        ElementAssertUtil.acceptAlert(alert);
    }

    @Then("the contact should be deleted from the Contact List and no longer visible")
    public void the_contact_should_be_deleted_from_the_contact_list_and_no_longer_visible() {
        waitUtil.waitForUrlToContain("/contactList");
        ElementAssertUtil.assertContactIsDeleted(driver, deletedContactIdentifier);
    }
}
