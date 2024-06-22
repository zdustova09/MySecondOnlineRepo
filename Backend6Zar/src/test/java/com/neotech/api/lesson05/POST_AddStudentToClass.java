package com.neotech.api.lesson05;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POST_AddStudentToClass {
	
	@Test
	public void addStudentToClass() {
		
		RestAssured.baseURI = APIConstants.Base_URI;
		
		String payload = "{\r\n"
				+ "  \"studentId\":" + APIGlobalVariables.studentId + ",\r\n"
				+ "  \"classId\":" + APIGlobalVariables.classId + "\r\n"
				+ "}";
		
		System.out.println(payload);
		
		RestAssured.given()
			.auth().oauth2(APIGlobalVariables.token)
			.body(payload)
			.contentType(ContentType.JSON)
		.when()
				.post(APIConstants.ADD_STUDENT_TO_CLASS_ENDPOINT)
				.prettyPeek()
		.then()
				.assertThat().statusCode(200)
				.and().body("result.action", Matchers.equalTo("Add student"))
				.and().body("result.success", Matchers.is(true));
	}

}
