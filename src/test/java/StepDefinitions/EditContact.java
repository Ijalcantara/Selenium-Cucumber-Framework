//package StepDefinitions;
//
//import java.util.List;
//import java.util.Map;
//
//import org.openqa.selenium.WebDriver;
//
//import com.cheq.contactlist.pages.ContactDetailPage;
//import com.cheq.contactlist.pages.ContactListPage;
//import com.cheq.contactlist.pages.EditContactPage;
//import com.cheq.contactlist.pages.LogInPage;
//import com.cheq.contactlist.utils.TestDataUtil;
//import com.cheq.contactlist.utils.WaitUtil;
//
//import Hooks.Hooks;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class EditContact {
//
//	private WebDriver driver;
//    private WaitUtil waitUtil;
//    private LogInPage loginPage;
//    private ContactListPage contactListPage;
//    private EditContactPage editContactPage;
//    private ContactDetailPage contactDetail;
//
//    public EditContact(Hooks hooks) {
//        this.driver = hooks.getDriver();
//        this.waitUtil = hooks.getWaitUtil();
//        this.loginPage = new LogInPage(driver);
//        this.contactListPage = new ContactListPage(driver);
//        this.editContactPage = new EditContactPage(driver);
//        this.contactDetail = new ContactDetailPage(driver);
//    }
//
//    @When("user enter required valid credentials")
//    public void user_enter_required_valid_credentials(DataTable dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> row : rows) {
//            String email = row.get("email");
//            String rawPassword = row.get("password");
//
//            String resolvedPassword = rawPassword.equals("{password}")
//                    ? TestDataUtil.getPassword(email)
//                    : rawPassword;
//
//            loginPage.enterEmail(email);
//            loginPage.enterPassword(resolvedPassword);
//        }
//    }
//
//    @And("click the editContact button")
//    public void click_the_edit_contact_button() {
//        loginPage.clickSubmitButton();
//    }
//
//    @Then("user can see all of the contact list")
//    public void user_can_see_all_of_the_contact_list() {
//        waitUtil.waitForUrlToContain("/contactList");
//        waitUtil.pause(2); 
//    }
//
//    @When("user click one contact in the table")
//    public void user_click_one_contact_in_the_table() {
//        contactListPage.clickAnyContactRow(); 
//    }
//
//    @And("hit EditContact button")
//    public void hit_edit_contact_button() {
//        contactDetail.clickEditContact(); 
//    }
//
//    @Then("user can edit details of the chosen contact")
//    public void user_can_edit_details_of_the_chosen_contact() {
//        waitUtil.waitForUrlToContain("/editContact");
//    }
//
//    @When("user enter new name")
//    public void user_enter_new_name(DataTable dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        for (Map<String, String> row : rows) {
//            editContactPage.enterFirstName(row.get("firstName"));
//            editContactPage.enterLastName(row.get("lastName"));
//        }
//    }
//
//    @When("hit the ediContact submit button")
//    public void hit_the_edi_contact_submit_button() {
//        editContactPage.clickSubmit();
//    }
//
//    @Then("user successfully edit the contact")
//    public void user_successfully_edit_the_contact() {
//    	waitUtil.sleep(1000); 
//        waitUtil.waitForUrlToContain("/contactList");
//    }
//}
//
