package com.neotech.api.lesson04;

import org.junit.Test;

import io.restassured.RestAssured;

public class Task01 {

	@Test
	public void generateTokenShorterWay() {
		 RestAssured.baseURI = "https://neo-api.azurewebsites.net";
		 String payload = "{\r\n"
		 		+ "  \"userNameOrEmailAddress\": \"Tester\",\r\n"
		 		+ "  \"password\": \"Student@Neo\",\r\n"
		 		+ "  \"rememberClient\": true\r\n"
		 		+ "}";
		 
		 // WE can run the payload test in one line including method, header, body requests in one line 
		 RestAssured.given().header("Content-Type", "application/json")
		 .body(payload)
		 .when().post("/api/TokenAuth/Authenticate") 
		 .prettyPeek()
		 .then().assertThat().statusCode(200);     
		 // prettyPrint() returns a string which beaks the chain  
		  
	}
	
}
