package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.anyOf;

import com.cheq.contactlist.utils.ResponseUtil;
import com.cheq.contactlist.utils.TestDataGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * Step definition for Adding New Contact using API
 */
public class AddNewContactAPI {
	
	private String token;
    private JsonNode contactData;
    private Response response;
    private String contactId;

    @Given("I load the auth token from {string}")
    public void i_load_the_auth_token_from(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        token = io.restassured.path.json.JsonPath.from(content).getString("token");
    }

    @And("I generate valid contact test data")
    public void i_generate_valid_contact_test_data() {
        String[] fields = {"firstName", "lastName", "email"};
        String dataGroup = TestDataGenerator.generateTestData(fields);
        contactData = TestDataGenerator.getTestData(dataGroup);
    }

    @Given("I generate contact test data with empty lastName")
    public void generateContactWithEmptyLastName() {
        contactData = TestDataGenerator.generateValidContact();
        ((ObjectNode) contactData).put("lastName", "");  
    }
    
    @When("I send POST request to {string} with the valid contact data and the loaded auth token")
    public void i_send_post_request_with_valid_data(String endpoint) {
        response = given()
            .baseUri("https://thinking-tester-contact-list.herokuapp.com")
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + token)
            .body(contactData.toString())
            .when()
            .post(endpoint);

        ResponseUtil.saveResponseToFile(response, "validContactResponse.json");
        contactId = response.jsonPath().getString("_id");
    }
    
    @When("I send POST request to {string} with the invalid contact data and the loaded auth token")
    public void i_send_post_request_to_with_the_invalid_contact_data_and_the_loaded_auth_token(String endpoint) {  	
    	response = given()
                .baseUri("https://thinking-tester-contact-list.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(contactData.toString())
            .when()
                .post(endpoint);
    	
    	ResponseUtil.saveResponseToFile(response, "validContactResponse.json");
    }

    @Then("the response status {int} should be displayed")
    public void the_response_status_should_be_displayed(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("the response body should contain all the contact details used plus the \"_id\" field")
    public void the_response_body_should_contain_contact_details_and_id() {
    	String firstName = contactData.path("firstName").asText();
    	String lastName = contactData.path("lastName").asText();
    	String email = contactData.path("email").asText();

    	response.then()
    	    .body("firstName", equalTo(firstName))
    	    .body("lastName", equalTo(lastName))
    	    .body("email", equalTo(email));
    }

    @Then("the response body should contain an error message indicating lastName is required or invalid")
    public void the_response_body_should_contain_an_error_message_indicating_last_name_is_required_or_invalid() {
    	response.then()
        .statusCode(400)
        .body("errors.lastName.message", anyOf(
            containsString("lastName"),
            containsString("required"),
            containsString("invalid")
        ));
    }
    
    @Then("I fetch contacts using the token and verify the new contact exists")
    public void i_fetch_contacts_and_verify_new_contact_exists() {
        given()
            .baseUri("https://thinking-tester-contact-list.herokuapp.com")
            .header("Authorization", "Bearer " + token)
            .when()
            .get("/contacts")
            .then()
            .body("find { it._id == '" + contactId + "' }.email", equalTo(contactData.get("email").asText()));
    }
}