package com.neotech.steps;

import org.hamcrest.Matchers;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateAClass {
    
    Response response;
    int id;

    @Given("a valid OAuth2 token is available")
    public void a_valid_o_auth2_token_is_available() {
        // Set base URI for API requests
        RestAssured.baseURI = APIConstants.Base_URI;
    }

    @Then("I create a new class and store the class id")
    public void i_create_a_new_class_and_store_the_class_id() {
        // Prepare payload for creating a new class
        String payload = "{\r\n"
                + "  \"name\": \"Cyber\",\r\n"
                + "  \"description\": \"NDE\",\r\n"
                + "  \"instructor\": \"Sabah\",\r\n"
                + "  \"instructorEmail\": \"sb@hmail.com\",\r\n"
                + "  \"term\": \"First\",\r\n"
                + "  \"credits\": 10\r\n"
                + "}";

        // Send POST request to create a new class and store the ID
        id = RestAssured.given()
                .auth().oauth2(APIGlobalVariables.token)
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post(APIConstants.CREATE_A_CLASS_ENDPOINT)
                .then()
                .log().all()
                .extract().path("result.id");
    }

    @Then("I get the class using the stored id")
    public void i_get_the_class_using_the_stored_id() {
        // Send GET request to retrieve the created class using stored ID
        response = RestAssured.given()
                .auth().oauth2(APIGlobalVariables.token)
                .pathParam("Id", id)
                .when()
                .get(APIConstants.GET_A_CLASS_ENDPOINT)
                .then()
                .log().all()
                .extract().response();
    }

    @Then("I validate the information of the retrieved class")
    public void i_validate_the_information_of_the_retrieved_class() {
        // Validate the retrieved class information
        response.then().statusCode(200);

        response.then().assertThat()
                .body("result.name", Matchers.equalTo(APIGlobalVariables.name))
                .body("result.description", Matchers.equalTo(APIGlobalVariables.description))
                .body("result.instructor", Matchers.equalTo(APIGlobalVariables.instructor))
                .body("result.instructorEmail", Matchers.equalTo(APIGlobalVariables.instructorEmail))
                .body("result.term", Matchers.equalTo(APIGlobalVariables.term))
                .body("result.credits", Matchers.equalTo(10));
    }
}