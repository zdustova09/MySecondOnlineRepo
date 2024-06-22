package com.neotech.steps;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateStudentSteps {
	
	Response response;
	int id;
	
	@When("I create a new student and store the student id")
	public void i_create_a_new_student_and_store_the_student_id() {
		
	   RestAssured.baseURI = APIConstants.Base_URI;
	   
	   String payload = "{\r\n"
				+ "  \"firstName\": \""+ APIGlobalVariables.firstName +"\",\r\n"
				+ "  \"lastName\": \""+APIGlobalVariables.lastName+"\",\r\n"
				+ "  \"email\": \""+ APIGlobalVariables.email +"\",\r\n"
				+ "  \"city\": \""+ APIGlobalVariables.city +"\",\r\n"
				+ "  \"state\": \""+ APIGlobalVariables.state +"\",\r\n"
				+ "  \"studentNumber\": \""+ APIGlobalVariables.studentNumber +"\"\r\n"
				+ "}";
	   
	   id = RestAssured.given()
			   .auth().oauth2(APIGlobalVariables.token)
			   .body(payload)
			   .contentType(ContentType.JSON)
			   .when()
			   .post(APIConstants.CREATE_STUDENT_ENDPOINT)
			   .prettyPeek()
			   .body().jsonPath().getInt("result.id");
	}
	@When("I get the student using the stored id")
	public void i_get_the_student_using_the_stored_id() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		response = RestAssured.given()
						.auth().oauth2(APIGlobalVariables.token)
						.queryParam("Id", id)
						.when()
							.get(APIConstants.GET_ONE_STUDENT_ENDPOINT)
							.prettyPeek();
	}
	@Then("I validate the information of the retrieved student")
	public void i_validate_the_information_of_the_retrieved_student() {
		
		
	    // 1st way - validate in response body
		
		response.then().assertThat().body("result.firstName", Matchers.equalTo(APIGlobalVariables.firstName));
		response.then().assertThat().body("result.lastName", Matchers.equalTo(APIGlobalVariables.lastName));
		response.then().assertThat().body("result.email", Matchers.equalTo(APIGlobalVariables.email));
		response.then().assertThat().body("result.city", Matchers.equalTo(APIGlobalVariables.city));
		response.then().assertThat().body("result.state", Matchers.equalTo(APIGlobalVariables.state));
		
		// 2nd way - validate studentNumber using JUnite Assertion 
		String studentNumber = response.body().jsonPath().getString("result.studentNumber");
		Assert.assertEquals(APIGlobalVariables.studentNumber, studentNumber);
		
		// Get all information of the student into a Map
		// Just to show you that we can do this process!
		
		Map<String, String> result = response.body().jsonPath().getMap("result");
		
		System.out.println("Map -> " + result);
		
		// A short task 
		// Iterate over the map!!
		
//		for (Map.Entry<String , String> entry: result.entrySet())
//		{
//			System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
		
		
	}

}