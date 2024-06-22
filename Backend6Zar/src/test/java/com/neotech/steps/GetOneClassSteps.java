package com.neotech.steps;

import com.neotech.utils.APIConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class GetOneClassSteps {
	RequestSpecification request;
	Response response;

	@Given("I create a request")
	public void i_create_a_request() {
		RestAssured.baseURI = APIConstants.Base_URI;
		request = RestAssured.given();
	}

	@Given("I provided the ClassId {int} as the path parameter")
	public void i_provided_the_class_id_as_the_path_parameter(Integer Id) {
		request.pathParam("Id", Id);

	}

	@When("I make a GET to the Get one class enpoint")
	public void i_make_a_get_to_the_get_one_class_enpoint() {
		response = request.when().get(APIConstants.GET_A_CLASS_ENDPOINT);
		response.prettyPeek();
	}

	@When("I validate that the status code is {int}")
	public void i_validate_that_the_status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@When("I validate that the Id in the response body is {int}")
	public void i_validate_that_the_id_in_the_response_body_is(Integer ClassId) {
		response.then().assertThat().body("result.id", equalTo(ClassId));

	}

	@Then("I validate that the class description contains {string}")
	public void i_validate_that_the_class_description_contains(String description) {
		response.then().assertThat().body("result.description", equalTo(description)); 

	}

}
