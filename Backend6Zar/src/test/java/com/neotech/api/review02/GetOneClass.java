package com.neotech.api.review02;

import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetOneClass {

	@Test
	public void getOneClassTest() {

		RestAssured.baseURI = APIConstants.Base_URI;

		RequestSpecification request = RestAssured.given();
		request.pathParam("Id", 273);

		Response response = request.when().get(APIConstants.GET_A_CLASS_ENDPOINT);
		response.prettyPeek();
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());

		response.then().assertThat().statusCode(200);

		System.out.println(response.headers());
		System.out.println("Server--->" + response.header("Server"));

		System.out.println("------------------------------------------------");

		System.out.println(response.asPrettyString());

		String desc = response.body().jsonPath().getString("result.description");
		response.then().assertThat().body("result.instructor", Matchers.is("Malik"));

		Assert.assertEquals("NDE", desc);

	}

}
