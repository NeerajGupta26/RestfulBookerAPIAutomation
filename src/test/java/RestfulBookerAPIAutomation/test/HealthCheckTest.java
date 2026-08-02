package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import base.BaseTest;

public class HealthCheckTest extends BaseTest {

    @Test(priority = 1)
    public void verifyHealthCheckStatusCode() {

        given()
            .spec(requestSpec)
        .when()
            .get("/ping")
        .then()
            .statusCode(201);
    }

    @Test(priority = 2)
    public void verifyHealthCheckResponseTime() {

        given()
            .spec(requestSpec)
        .when()
            .get("/ping")
        .then()
            .statusCode(201)
            .time(lessThan(3000L));
    }

    @Test(priority = 3)
    public void verifyHealthCheckWithoutAuthentication() {

        given()
            .spec(requestSpec)
        .when()
            .get("/ping")
        .then()
            .statusCode(201);
    }
}
