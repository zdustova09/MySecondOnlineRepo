package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Delete_RemoveStudent {
	
	@Test
	public void deleteStudent() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		RestAssured
				.given()
						.auth().oauth2(APIGlobalVariables.token)
						.queryParam("Id", APIGlobalVariables.studentId)
				.when()
						.request(Method.DELETE, APIConstants.DELETE_STUDENT_ENDPOINT)
						.prettyPeek()
				.then()
						.assertThat().statusCode(200);
	}

}
