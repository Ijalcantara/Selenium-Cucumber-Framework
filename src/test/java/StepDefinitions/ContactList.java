//package StepDefinitions;
//
//import java.util.List;
//import java.util.Map;
//
//import org.openqa.selenium.WebDriver;
//
//import com.cheq.contactlist.pages.LogInPage;
//import com.cheq.contactlist.utils.AssertionUtil;
//import com.cheq.contactlist.utils.TestDataUtil;
//import com.cheq.contactlist.utils.WaitUtil;
//
//import Hooks.Hooks;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class ContactList {
//
//	private WebDriver driver;
//	private WaitUtil waitUtil;
//    private LogInPage loginPage;
//
//    public ContactList(Hooks hooks) {
//        this.driver = hooks.getDriver();
//        this.waitUtil = hooks.getWaitUtil();
//        this.loginPage = new LogInPage(driver);
//    }
//    
//	@Given("user is in login page")
//	public void user_is_in_login_page() {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//	}
//
//	@When("user input a valid credentials")
//	public void user_input_a_valid_credentials(DataTable dataTable) {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
//	    //
//	    // For other transformations you can register a DataTableType.
//	    //throw new io.cucumber.java.PendingException();
//		//loginPage.enterEmail(null);
//		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//	    for (Map<String, String> row : rows) {
//	        String email = row.get("email");
//	        String rawPassword = row.get("password");
//
//	        // If placeholder, resolve from JSON
//	        String resolvedPassword = rawPassword.equals("{password}")
//	                ? TestDataUtil.getPassword(email)
//	                : rawPassword;
//
//	        loginPage.enterEmail(email);
//	        loginPage.enterPassword(resolvedPassword);
//        }
//	}
//
//	@When("hit the Submit button")
//	public void hit_the_submit_button() {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//		loginPage.clickSubmitButton();
//	}
//
//	@Then("user is redirected to contact details page.")
//	public void user_is_redirected_to_contact_details_page() {
//	    // Write code here that turns the phrase above into concrete actions
//	    //throw new io.cucumber.java.PendingException();
//		waitUtil.pause(2);
//		boolean redirected = waitUtil.waitForUrlToContain("/contactList");
//        AssertionUtil.assertTrue(redirected, "User should be redirected to Contact List page.");
//	}
//}
