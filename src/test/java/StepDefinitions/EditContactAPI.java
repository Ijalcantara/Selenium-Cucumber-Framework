package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.anyOf;

import com.cheq.contactlist.utils.TestDataGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EditContactAPI {

    private String token;
    private String contactId;
    private JsonNode contactData;
    private Response response;

    @Given("auth token is loaded from {string}")
    public void auth_token_is_loaded_from(String filePath) throws IOException {
    	String content = new String(Files.readAllBytes(Paths.get(filePath)));
      token = io.restassured.path.json.JsonPath.from(content).getString("token");
    }

    @Given("I load contactId from {string}")
    public void i_load_contact_id_from(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        contactId = io.restassured.path.json.JsonPath.from(content).getString("_id");
    }

    @Given("I generate valid contact test data for update")
    public void i_generate_valid_contact_test_data_for_update() {
        String[] fields = {"firstName", "lastName", "email", "phoneNumber"};
        String dataGroup = TestDataGenerator.generateTestData(fields);
        contactData = TestDataGenerator.getTestData(dataGroup);
    }

    @Given("I generate invalid contact test data for update")
    public void i_generate_invalid_contact_test_data_for_update() {
        contactData = TestDataGenerator.generateValidContact();
        ((ObjectNode) contactData).put("lastName", "");
    }

    @When("I send PUT request to {string} with the updated contact data and the loaded auth token")
    public void i_send_put_request_with_updated_data(String endpointTemplate) {
        String endpoint = endpointTemplate.replace("{contactId}", contactId);

        response = given()
            .baseUri("https://thinking-tester-contact-list.herokuapp.com")
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + token)
            .body(contactData.toString())
            .when()
            .put(endpoint);
    }

    @Then("the {int} response should be displayed")
    public void the_response_should_be_displayed(Integer expectedStatusCode) {
    	response.then().statusCode(expectedStatusCode);
    }
    
    @And("the response body should contain all the updated contact details used plus the \"contactId\" field")
    public void the_response_body_should_contain_all_updated_contact_details_plus_contactId() {
        response.then()
            .body("firstName", equalTo(contactData.path("firstName").asText()))
            .body("lastName", equalTo(contactData.path("lastName").asText()))
            .body("email", equalTo(contactData.path("email").asText()))
            .body("_id", equalTo(contactId));
    }

    @And("the response body should contain an error message indicating the invalid fields")
    public void the_response_body_should_contain_error_message_for_invalid_fields() {
        response.then()
            .statusCode(400)
            .body("errors.lastName.message", anyOf(
                containsString("lastName"),
                containsString("required"),
                containsString("invalid")
            ));
    }
}