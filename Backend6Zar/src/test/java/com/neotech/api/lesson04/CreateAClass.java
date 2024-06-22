package com.neotech.api.lesson04;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import io.restassured.RestAssured;

public class CreateAClass {

    @Test
    public void createAClass() {
        // Set the base URI for the REST API
        RestAssured.baseURI = "https://neo-api.azurewebsites.net";

        // Payload for authentication
        String authPayload = "{\r\n" +
                "  \"userNameOrEmailAddress\": \"Tester\",\r\n" +
                "  \"password\": \"Student@Neo\",\r\n" +
                "  \"rememberClient\": true\r\n" +
                "}";

        // Fetch the access token
        String token = 
            given()
                .header("Content-Type", "application/json")
                .body(authPayload)
            .when()
                .post("/api/TokenAuth/Authenticate")
            .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("result.accessToken");

        // Print the token for debugging purposes
        System.out.println("Token: " + token);

        // Payload to create a class
        String createClassPayload = "{\r\n" +
                "  \"name\": \"Cyber\",\r\n" +
                "  \"description\": \"NDE Essentials\",\r\n" +
                "  \"instructor\": \"Zarina\",\r\n" +
                "  \"instructorEmail\": \"zd@gmail.com\",\r\n" +
                "  \"term\": \"SummerTerm\",\r\n" +
                "  \"credits\": 10\r\n" +
                "}";

        // Create a class using the access token and assert the response
        given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + token)
            .body(createClassPayload)
        .when()
            .post("/api/services/app/Class/Create")
        .then()
            .statusCode(200)
            .assertThat()
            .body("result.name", equalTo("Cyber"))
            .body("result.instructor", equalTo("Zarina"))
            .body("result.instructorEmail", equalTo("zd@gmail.com"))
            .body("result.term", equalTo("SummerTerm"))
            .body("result.credits", equalTo(10))
            .log().all(); // Log the response for debugging 
    }
}