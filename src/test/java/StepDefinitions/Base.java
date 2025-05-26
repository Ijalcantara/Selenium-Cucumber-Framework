package StepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cheq.contactlist.pages.BasePage;
import com.cheq.contactlist.pages.LogInPage;
import com.cheq.contactlist.utils.TestDataUtil;
import com.cheq.contactlist.utils.WaitUtil;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Base {
	
	private WebDriver driver;
	private WaitUtil waitUtil;
    private LogInPage loginPage;
    private BasePage basePage;

    public Base(Hooks hooks) {
        this.driver = hooks.getDriver();
        this.waitUtil = hooks.getWaitUtil();
        this.loginPage = new LogInPage(driver);
        this.basePage = new BasePage(driver);
    }
    
	@When("user enter a valid credentials")
	public void user_enter_a_valid_credentials(DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> row : rows) {
	        String email = row.get("email");
	        String rawPassword = row.get("password");

	        // If placeholder, resolve from JSON
	        String resolvedPassword = rawPassword.equals("{password}")
	                ? TestDataUtil.getPassword(email)
	                : rawPassword;

	        loginPage.enterEmail(email);
	        loginPage.enterPassword(resolvedPassword);
	}
}
	
	@And("user click the logut button")
	public void user_click_the_logut_button() {
		basePage.clickLogOutButton();
	}

	@Then("user is navigated to login page")
	public void user_is_navigated_to_login_page() {
		waitUtil.pause(2);
		boolean redirected = waitUtil.waitForUrlToContain("https://thinking-tester-contact-list.herokuapp.com/");
	}
}
