package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Map;
import java.util.HashMap;

import com.cheq.contactlist.utils.ApiFileUtil;
import com.cheq.contactlist.utils.ApiUtil;
import com.cheq.contactlist.utils.ElementAssertUtil;
import com.cheq.contactlist.utils.ResponseUtil;

public class LoginAPI {

	private Map<String, Object> loginPayload;
	private Response response;

	@Given("I have {string} login credentials from json file {string}")
	public void i_have_login_credentials_from_json_file(String credentialType, String jsonFileName) {
		Map<String, Object> fullJson = ApiFileUtil.loadJsonAsMap(jsonFileName);

		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> credentials = (Map<String, Map<String, String>>) fullJson.get("loginCredentials");
		Map<String, String> selectedCredentials = credentials.get(credentialType);

		loginPayload = new HashMap<>();
		loginPayload.put("email", selectedCredentials.get("email"));
		loginPayload.put("password", selectedCredentials.get("password"));
	}

	@When("I send a POST request to {string}")
	public void i_send_a_post_request_to(String endpoint) {
		response = ApiUtil.post(endpoint, loginPayload);
		ResponseUtil.saveResponseToFile(response, "loginResponse.json");
	}

	@Then("response status code of request must be {int}")
	public void response_status_code_of_request_must_be(Integer expectedStatusCode) {
		ElementAssertUtil.assertStatusCode(response.getStatusCode(), expectedStatusCode);
		ResponseUtil.saveResponseToFile(response, "loginResponse.json");
	}

	@Then("the response body should contain user object and token string")
	public void the_response_body_should_contain_user_object_and_token_string() {
		Map<String, Object> responseBody = response.jsonPath().getMap("$");

		Object userObj = responseBody.get("user");
		Object tokenObj = responseBody.get("token");

		System.out.println("User object: " + userObj);
		System.out.println("Token string: " + tokenObj);
	}

	@Then("user must not log in")
	public void user_must_not_log_in() {

	}
}