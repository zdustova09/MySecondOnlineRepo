package com.neotech.api.lesson05;

import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

public class GET_AllStudents {
    
    @Test
    public void getAllStudents() {
        RestAssured.baseURI = APIConstants.Base_URI;

        // BDD: given() --> when() --> then() --> and then() etc
        given()
            .auth().oauth2(APIGlobalVariables.token)
        .when()
            .get(APIConstants.GET_ALL_STUDENTS_ENDPOINT)
            .prettyPeek()
        .then()
            .assertThat().statusCode(200)
            .and()
            .body("result.totalCount", equalTo(38))
            .and()
            .body("result.items.size()", is(10));
    }
}