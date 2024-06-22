package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class DELETE_A_Class {
	
	@Test
	public void deleteAClass() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		RestAssured
				.given()
						.auth().oauth2(APIGlobalVariables.token)
						.queryParam("Id", APIGlobalVariables.classId)
				.when()
						.request(Method.DELETE, APIConstants. DELETE_A_CLASS_ENDPOINT)
						.prettyPeek()
				.then()
						.assertThat().statusCode(200); 
	}

}
