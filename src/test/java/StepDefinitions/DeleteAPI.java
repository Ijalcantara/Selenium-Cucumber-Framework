package StepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.path.json.JsonPath;

public class DeleteAPI {

	private String token;
    private String contactId;
    private Response response;

    @Given("token is loaded from {string}")
    public void token_is_loaded_from(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        token = JsonPath.from(content).getString("token");
    }

    @Given("contactId is loaded from {string}")
    public void contact_id_is_loaded_from(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        contactId = JsonPath.from(content).getString("_id");
    }
    
    @Given("the contact exists on the server")
    public void the_contact_exists_on_the_server() {
        given()
          .baseUri("https://thinking-tester-contact-list.herokuapp.com")
          .header("Authorization", "Bearer " + token)
          .when()
          .get("/contacts/" + contactId)
          .then()
          .statusCode(200);
    }

    @When("I send DELETE request to {string}")
    public void i_send_delete_request_to(String endpointTemplate) {
        String endpoint = endpointTemplate.replace("{contactId}", contactId);

        response = given()
            .baseUri("https://thinking-tester-contact-list.herokuapp.com")
            .header("Authorization", "Bearer " + token)
            .when()
            .delete(endpoint);
    }
    
    @Then("{int} response status is displayed")
    public void response_status_is_displayed(Integer expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

}
