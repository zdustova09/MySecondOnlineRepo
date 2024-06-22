package com.neotech.api.review02;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class POST_CreatClass {
	
	@Test 
	public void creatClassTest() {
		
		RestAssured.baseURI= APIConstants.Base_URI;
		
		String payload = "{\r\n"
				+ "  \"name\": \"Truth\",\r\n"
				+ "  \"description\": \"Telling the truth\",\r\n"
				+ "  \"instructor\": \"Damla\",\r\n"
				+ "  \"instructorEmail\": \"damla@gmail.com\",\r\n"
				+ "  \"term\": \"Second\",\r\n"
				+ "  \"credits\": 1\r\n"
				+ "}"; 
		
		
		System.out.println(payload);
		
		RestAssured.given().auth()
		.oauth2(APIGlobalVariables.token)
		.contentType(ContentType.JSON)
		.body(payload)
		.when()
		// .post(APIConstants.CREATE_A_CLASS_ENDPOINT)
		.request(Method.POST,APIConstants.CREATE_A_CLASS_ENDPOINT)
		.prettyPeek()
		.then() 
		.assertThat().statusCode(200)
		.and()
		.assertThat().body("result.name", Matchers.equalTo("Truth"))
		.and()
		.assertThat().body("result.instructor", Matchers.equalTo("Damla"));  
		
		
	}

}
