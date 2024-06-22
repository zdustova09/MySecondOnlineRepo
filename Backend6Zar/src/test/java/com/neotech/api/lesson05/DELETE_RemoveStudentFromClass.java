package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;


public class DELETE_RemoveStudentFromClass {
	
	@Test
	public void removeStudent() {
		RestAssured.baseURI = APIConstants.Base_URI;
		
		RestAssured
				.given()
						.auth().oauth2(APIGlobalVariables.token)
						.queryParam("StudentId", APIGlobalVariables.studentId)
						.queryParam("ClassId", APIGlobalVariables.classId)
				.when()
						.delete(APIConstants.REMOVE_STUDENT_FROM_CLASS_ENDPOINT)
						.prettyPeek()
				.then()
						.assertThat().statusCode(200);
	}

} 
