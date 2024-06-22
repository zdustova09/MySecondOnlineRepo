package com.neotech.api.lesson05;

import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GET_AClass { 
  

	 
	@Test
	public void getClassRoster() {
		RestAssured.baseURI = APIConstants.Base_URI;
		
		RestAssured
				.given()
						.auth().oauth2(APIGlobalVariables.token)
						.pathParam("Id", APIGlobalVariables.classId)
				.when()
						.request(Method.GET, APIConstants.GET_A_CLASS_ENDPOINT)
						.prettyPeek()
				.then()
						.assertThat().statusCode(200)
						.body("result.id", equalTo(228));
						
		
	}

}


