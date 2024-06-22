package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_CreateAClass {
	
	@Test
	public void createAClass() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		String payload = "{\r\n"
				+ "  \"name\": \""+APIGlobalVariables.name+"\",\r\n"
				+ "  \"description\": \""+APIGlobalVariables.description+"\",\r\n"
				+ "  \"instructor\": \""+APIGlobalVariables.instructor+"\",\r\n"
				+ "  \"instructorEmail\": \""+APIGlobalVariables.instructorEmail+"\",\r\n"
				+ "  \"term\": \""+ APIGlobalVariables.term+"\",\r\n"
				+ "  \"credits\": "+ APIGlobalVariables.credits+"\r\n" 
				+ "}";
		
		System.out.println(payload);
		
		RestAssured.given()
	    .auth().oauth2(APIGlobalVariables.token)
	    .body(payload)
	    .contentType(ContentType.JSON)
		.when()
		.post(APIConstants.CREATE_A_CLASS_ENDPOINT )
		.prettyPeek()
		.then()
		.assertThat().statusCode(200)
		.and().body("result.name", Matchers.is(APIGlobalVariables.name))
		.and().body("result.instructorEmail", Matchers.is(APIGlobalVariables.instructorEmail));
	}

}
