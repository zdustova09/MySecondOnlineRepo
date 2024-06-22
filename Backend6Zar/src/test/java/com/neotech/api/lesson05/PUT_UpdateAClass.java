package com.neotech.api.lesson05;
import org.junit.Assert;
import org.junit.Test;
import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class PUT_UpdateAClass {
    
    @Test
    public void updateAClass() { 
        
        RestAssured.baseURI = APIConstants.Base_URI;
        
        String payload = "{\r\n"
                + "  \"id\": " + APIGlobalVariables.classId + ",\r\n"
                + "  \"name\": \"Ethical Hacking\",\r\n"
                + "  \"description\": \"EHE Intro\",\r\n"
                + "  \"instructor\": \"Sabah\",\r\n"
                + "  \"instructorEmail\": \"sb@gmail.com\",\r\n"
                + "  \"term\": \"Second\",\r\n"
                + "  \"credits\": 10\r\n"
                + "}";
        
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + APIGlobalVariables.token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .request(Method.PUT, APIConstants.UPDATE_A_CLASS_ENDPOINT)
                .prettyPeek();
    
        
        response.then().assertThat().statusCode(200);
        int id = response.body().jsonPath().getInt("result.id"); 
        Assert.assertEquals(APIGlobalVariables.classId, id);
    }
}