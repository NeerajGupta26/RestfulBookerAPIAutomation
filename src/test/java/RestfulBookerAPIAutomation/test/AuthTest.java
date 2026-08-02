package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import base.BaseTest;

public class AuthTest extends BaseTest {

    @Test(priority = 4)
    public void verifyAuthToken() {

        Map<String, String> authBody = new HashMap<>();

        authBody.put("username", configReader.getProperty("username"));
        authBody.put("password", configReader.getProperty("password"));

        Response response =
                given()
                        .spec(requestSpec)
                        .body(authBody)
                .when()
                        .post("/auth");

        response.then()
                .statusCode(200)
                .time(lessThan(5000L));
token = response.jsonPath().getString("token");

System.out.println("Generated Token = " + token);
    }

    @Test(priority = 5)
    public void verifyWithInvalidUsername() {
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", configReader.getProperty("invalid_username"));
        authBody.put("password", configReader.getProperty("password"));

        given()
            .spec(requestSpec)
            .body(authBody)
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
    }

    @Test(priority = 6)
    public void verifyWithInvalidPassword() {
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", configReader.getProperty("username"));
        authBody.put("password", configReader.getProperty("invalid_password"));

        given()
            .spec(requestSpec)
            .body(authBody)
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
    }

    @Test(priority = 7)
    public void verifyUsernameFieldEmpty() {
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", "");
        authBody.put("password", configReader.getProperty("password"));

        given()
            .spec(requestSpec)
            .body(authBody)
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
    }

    @Test(priority = 8)
    public void verifyPasswordFieldEmpty() {
        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", configReader.getProperty("username"));
        authBody.put("password", "");

        given()
            .spec(requestSpec)
            .body(authBody)
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
    }

    @Test(priority = 9)
    public void verifyWithEmptyBody() {
        given()
            .spec(requestSpec)
            .body("{}")
            .when()
            .post("/auth")
            .then()
            .statusCode(200)
            .time(lessThan(5000L));
    }
}
