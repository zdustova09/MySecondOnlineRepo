package com.neotech.steps;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import com.neotech.utils.APIConstants;
import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateTokenSteps {
	
	RequestSpecification request;
	Response response;
	
	@Given("I create a token request")
	public void i_create_a_token_request() {
		RestAssured.baseURI = APIConstants.Base_URI;
		request = RestAssured.given();
	}
	@Given("I provide the header information")
	public void i_provide_the_header_information() {
	   request.contentType(ContentType.JSON);
	   //2nd way
	   //request.header("Content-Type", "application/json");
	}
	@Given("I provide the body information")
	public void i_provide_the_body_information() {
		
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		
		String username = ConfigsReader.getProperty("APIUsername");
		String password = ConfigsReader.getProperty("APIPassword");
		
		String payload = "{\r\n"
				+ "  \"userNameOrEmailAddress\": \"" + username + "\",\r\n"
				+ "  \"password\": \"" + password + "\",\r\n"
				+ "  \"rememberClient\": true\r\n"
				+ "}";
		
	    request.body(payload);
	}
	@When("I make a POST request to generate token endpoint")
	public void i_make_a_post_request_to_generate_token_endpoint() {
	   response = request.when().post(APIConstants.GENERATE_TOKEN_ENDPOINT);
	   // 2nd way
	  // response = request.when().request(Method.POST, APIConstants.GENERATE_TOKEN_ENDPOINT);
	   response.prettyPeek();
	}
	@Then("I validate the status code is {int}")
	public void i_validate_the_status_code_is(Integer statusCode) {
	    response.then().assertThat().statusCode(statusCode);
	}
	@Then("I validate that the body contains {string}")
	public void i_validate_that_the_body_contains(String str) {
	   response.then().assertThat().body(containsString(str));
	}
	@Then("I validate that the value of {string} is true")
	public void i_validate_that_the_value_of_is_true(String key) {
	    response.then().assertThat().body(key, is(true));
	}

}