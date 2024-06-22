package com.neotech.api.lesson04;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Get_AllClasses {
	public static void main(String[] args) {

		// Set the Base URI for all API calls
		RestAssured.baseURI = "https://neo-api.azurewebsites.net";

		System.out.println(RestAssured.baseURI);

		// Build a request: method, base uri, endpoint, parameters {if any}
		RequestSpecification request = RestAssured.given();

		// Any parameter, auth would need to be specified before the request is sent
		// Send the request to the end point and get the response back
		Response response = request.when().get("/api/services/app/Class/GetAll");
		System.out.println(response.statusCode());

		System.out.println("---------------------------------");
		// Get headers
		System.out.println(response.headers());
		// we can create an object and deal with that
		// Headers headers = response.headers();
		// response.header("Content-Type");
		System.out.println(response.header("Content-Type"));

		System.out.println("-------------------------------------------");
		
		ResponseBody body = response.getBody();
		// System.out.println(body.asPrettyString());

		// We can also directly display the response body
		response.prettyPrint();

	}
}
