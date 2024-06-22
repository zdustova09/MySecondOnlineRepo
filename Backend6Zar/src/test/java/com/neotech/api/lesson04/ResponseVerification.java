package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseVerification {

    @Test
    public void verifyAllBooksRequest() {
        
        // Set the base URI
        RestAssured.baseURI = "https://neo-api.azurewebsites.net";
        System.out.println(RestAssured.baseURI);

        // During the request specification, before we make a call, we can include any 
        // necessary information
        RequestSpecification request = RestAssured.given();
        request.queryParam("MaxResultCount", 100);

        Response response = request.when().get("/api/services/app/Class/GetAll");

        // Validate that the response status code is 200
        response.then().assertThat().statusCode(200);

        // Verify using JUnit Assertion
        Assert.assertEquals(200, response.statusCode());

        // Verify that the content-type is application/json
        response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");

        // Using JUnit
        Assert.assertEquals("application/json; charset=utf-8", response.header("Content-Type"));

        // Validate that the response body contains the string 'Jenkins'
        String body = response.body().asString();
        Assert.assertTrue("Jenkins not found", body.contains("Jenkins"));

        // We can use a one liner
        Assert.assertTrue(response.body().asString().contains("Jenkins"));
        
        
        
        
    }
}