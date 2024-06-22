package com.neotech.api.lesson05;
import org.junit.Assert;
import org.junit.Test;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GET_OneStudent {
    
    @Test
    public void getOneStudent() {
        
        RestAssured.baseURI = APIConstants.Base_URI;
        Response response = RestAssured.given()
                .auth().oauth2(APIGlobalVariables.token)
                .queryParam("Id", APIGlobalVariables.studentId) // Corrected the variable name from studetnId to studentId
                .when()
                .get(APIConstants.GET_ONE_STUDENT_ENDPOINT)
                .prettyPeek();
        
        System.out.println("------------------------------------------");
        
        // Verify the statusCode is 200 
        response.then().assertThat().statusCode(200);
        
        // Print Content-Type header
        System.out.println(response.header("Content-Type"));
        
        // Get info from the body
        int id = response.body().jsonPath().getInt("result.id"); // Corrected the JSON path to get the id from the result object
        System.out.println("id -> " + id);
        
        // Verify that the parameter studentId is equal to the id result 
        Assert.assertEquals(APIGlobalVariables.studentId, id); 
    }
}
