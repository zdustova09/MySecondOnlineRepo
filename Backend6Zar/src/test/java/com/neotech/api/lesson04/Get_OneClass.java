package com.neotech.api.lesson04;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get_OneClass {

    @Test
    public void getOneClassTest() {

        baseURI = "https://neo-api.azurewebsites.net";

        RequestSpecification request = given();

        // Setting the path parameter
        // It can be the documentation query request on the webpage
        request.pathParam("Id", 69);

        // Making a call
        Response response = request.when().get("/api/services/app/Class/Get/{Id}");

        // Validate the status code
        System.out.println("Status Code:" + response.statusCode());

        // Verify the status code
        response.then().assertThat().statusCode(200);

        response.prettyPeek();

        // Assert using Hamcrest Library
        response.then().assertThat().body("result.name", equalTo("Cyber"));
        response.then().assertThat().body("result.description", containsString("NDE"));
        response.then().assertThat().body("result.instructorEmail", endsWith("m@gmail.com"));
    }
}
