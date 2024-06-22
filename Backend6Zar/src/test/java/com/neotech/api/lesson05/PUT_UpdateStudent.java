package com.neotech.api.lesson05;

import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class PUT_UpdateStudent {
	
	@Test
	public void updateStudent() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		// The request needs 3 items
			//1. auth token
			//2. content type
			//3. payload/body
		
		String payload = "{\r\n"
				+ "  \"id\": " + APIGlobalVariables.studentId + ",\r\n"
				+ "  \"firstName\": \"Mila\",\r\n"
				+ "  \"lastName\": \"Mila\",\r\n"
				+ "  \"email\": \"Mila@neotech.com\",\r\n"
				+ "  \"city\": \"DC\",\r\n"
				+ "  \"state\": \"NY\",\r\n"
				+ "  \"studentNumber\": \"008\"\r\n"
				+ "}";
		
		// System.out.println(payload);
		// 1st way - without saving the response 
		
//		RestAssured
//			.given()
//					.header("Authorization", "Bearer " + APIGlobalVariables.token)
//					.contentType(ContentType.JSON)
//					.body(payload)
//			.when()
//					.request(Method.PUT, APIConstants.UPDATE_STUDENT_ENDPOINT)
//					.prettyPeek()
//			.then()
//					.assertThat().statusCode(200);
		
		// 2nd way - save the response and do further checks
		Response response = RestAssured
				.given()
				.header("Authorization", "Bearer " + APIGlobalVariables.token)
				.contentType(ContentType.JSON)
				.body(payload)
		.when()
				.request(Method.PUT, APIConstants.UPDATE_STUDENT_ENDPOINT)
				.prettyPeek();
	
		// Then we will validate
		response.then().assertThat().statusCode(200);
		
		// We will check if the response contains certain value
		int id = response.body().jsonPath().getInt("result.id");
		
		// Verify that the parameter studentId is equal to the id result
		Assert.assertEquals(APIGlobalVariables.studentId, id);
		
		//Negative Testing 
		//Verify that the response does not contain a certain value
		Assert.assertFalse(response.body().jsonPath().getString("result").contains(APIGlobalVariables.lastName));
		
		
	}

}