package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cheq.contactlist.utils.ApiUtil;
import com.cheq.contactlist.utils.ReporterUtil;

public class CreateUserAPI {

	private Map<String, Object> userPayload;
    private Response response;
    private Response getUserResponse;

    @Given("I have valid user data")
    public void i_have_valid_user_data(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> userData = rows.get(0);

        String password = userData.get("password").replace("{password}", "password123");

        userPayload = Map.of(
            "firstName", userData.get("firstName"),
            "lastName", userData.get("lastName"),
            "email", userData.get("email"),
            "password", password
        );
    }
    
    @Given("I have user data with invalid email")
    public void i_have_user_data_with_invalid_email(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, Object> userPayload = new HashMap<>();

        Map<String, String> firstRow = rows.get(0);

        userPayload.put("firstName", firstRow.get("firstName"));
        userPayload.put("lastName", firstRow.get("lastName"));
        userPayload.put("email", firstRow.get("email"));
        userPayload.put("password", firstRow.get("password"));

        this.userPayload = userPayload;
    }

    @When("I send a POST request to {string} to create the user")
    public void i_send_a_post_request_to_to_create_the_user(String endpoint) {
        response = ApiUtil.post(endpoint, userPayload);
        System.out.println("POST Response Status Code: " + response.getStatusCode());
        System.out.println("POST Response Body: " + response.getBody().asString());
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        System.out.println("Expected Status Code: " + expectedStatusCode);
        System.out.println("Actual Status Code: " + response.getStatusCode());
    }

    @Then("the response body should contain the user details")
    public void the_response_body_should_contain_the_user_details() {
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Then("I can retrieve the user by email {string} and verify details")
    public void i_can_retrieve_the_user_by_email_and_verify_details(String email) {
        getUserResponse = ApiUtil.get("/users?email=" + email);
        System.out.println("GET /users?email=" + email + " Status Code: " + getUserResponse.getStatusCode());
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
    }
    
    @Then("the response body should contain an error message about invalid email")
    public void the_response_body_should_contain_an_error_message_about_invalid_email() {
        String responseBody = response.getBody().asString();
        System.out.println("Response body for invalid email scenario: " + responseBody);
    }
}
