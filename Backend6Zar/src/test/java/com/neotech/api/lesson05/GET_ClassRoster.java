package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import static org.hamcrest.Matchers.*;

public class GET_ClassRoster {
	
	@Test
	public void getClassRoster() {
		RestAssured.baseURI = APIConstants.Base_URI;
		
		RestAssured
				.given()
						.auth().oauth2(APIGlobalVariables.token)
						.queryParam("classId", APIGlobalVariables.classId)
				.when()
						.request(Method.GET, APIConstants.GET_CLASS_ROSTER_ENDPOINT)
						.prettyPeek()
				.then()
						.assertThat().statusCode(200)
						.body("result.studentCount", equalTo(0))
						.body("result.students.size()", is(0));
		
	}

}


