package com.neotech.api.lesson05;


import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_CreateStudent {
	
	@Test
	public void createStudentTest() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		String payload = "{\r\n"
				+ "  \"firstName\": \""+ APIGlobalVariables.firstName +"\",\r\n"
				+ "  \"lastName\": \""+APIGlobalVariables.lastName+"\",\r\n"
				+ "  \"email\": \""+ APIGlobalVariables.email +"\",\r\n"
				+ "  \"city\": \""+ APIGlobalVariables.city +"\",\r\n"
				+ "  \"state\": \""+ APIGlobalVariables.state +"\",\r\n"
				+ "  \"studentNumber\": \""+ APIGlobalVariables.studentNumber +"\"\r\n"
				+ "}";
		
		System.out.println(payload);
		
		RestAssured.given()
			.auth().oauth2(APIGlobalVariables.token)
			.body(payload)
			.contentType(ContentType.JSON)
			.when()
					.post(APIConstants.CREATE_STUDENT_ENDPOINT)
					.prettyPeek()
			.then()
					.assertThat().statusCode(200)
					.and().body("result.firstName", Matchers.is(APIGlobalVariables.firstName))
					.and().body("result.email", Matchers.is(APIGlobalVariables.email));
	}
}