package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;

import io.restassured.RestAssured;

import io.restassured.specification.RequestSpecification;

public class Post_GenerateToken {
	
	   @Test
	    public void generateToken() {

	        // Set the base URI
	        RestAssured.baseURI = APIConstants.Base_URI;
	        System.out.println(RestAssured.baseURI);

	        // Create a request
	        RequestSpecification request = RestAssured.given();

	        // Set the request header Content-Type to application/json
	        request.header("Content-Type", "application/json");

	        // Send the payload -- it is a body in documentation on the website
	        String payload = "{\r\n" +
	                "  \"userNameOrEmailAddress\": \"Tester\",\r\n" +
	                "  \"password\": \"Student@Neo\",\r\n" +
	                "  \"rememberClient\": true\r\n" +
	                "}";
	        
	        System.out.println("------------Pretty Peak-----------------");

	        // Make the call (POST)
	        RestAssured.given()
	         .header("Content-Type", "application/json")
			 .body(payload)
			 .when().post(APIConstants.GENERATE_TOKEN_ENDPOINT)  
			 .prettyPeek()
			 .then().assertThat().statusCode(200);   

    }
}