package com.neotech.api.lesson04;

import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_GenerateToken {

    @Test
    public void generateToken() {

        // Set the base URI
        RestAssured.baseURI = "https://neo-api.azurewebsites.net";
        System.out.println(RestAssured.baseURI);

        // Create a request
        RequestSpecification request = RestAssured.given();

        // Set the request header Content-Type to application/json
        request.header("Content-Type", "application/json");

        // Send the payload -- it is a body in documentation on the website
        String payload = "{\r\n" +
                "  \"userNameOrEmailAddress\": \"Tester\",\r\n" +
                "  \"password\": \"Student@Neo\",\r\n" +
                "  \"rememberClient\": true\r\n" +
                "}";
        
        // Request has:
           // method 
           // header
            // body  

        System.out.println(payload);

        request.body(payload);

        // Make the call (POST)
        Response response = request.when().post("/api/TokenAuth/Authenticate");

        // Print the response details
        System.out.println("-------------------------------------");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("-------------------------------------");
        System.out.println(response.getStatusLine());

        response.prettyPrint();

        // Assert the status code is 200
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(200, response.statusCode());
    }
}