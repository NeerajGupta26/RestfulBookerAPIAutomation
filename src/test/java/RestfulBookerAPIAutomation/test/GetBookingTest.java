package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import io.restassured.response.Response;

import org.testng.Assert;

public class GetBookingTest extends BaseTest {

    @Test(priority = 20)
    public void get_booking() {

        given()
            .spec(requestSpec)
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }

    @Test(priority = 21)
    public void verifyBookingListReturned() {

        Response response =
                given()
                        .spec(requestSpec)
                .when()
                        .get("/booking");

        // Status Code Validation
        response.then().statusCode(200);

        // Response Body Empty Check
        String responseBody = response.asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response Body is Empty");

        // bookingid field present
        response.then().body("[0]", hasKey("bookingid"));
    }

    @Test(priority = 22)
    public void filter_by_firstname() {

        given()
            .spec(requestSpec)
            .queryParam("firstname", "neeraj")
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }

    @Test(priority = 23)
    public void filter_by_lastname() {

        given()
            .spec(requestSpec)
            .queryParam("lastname", "gupta")
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }

    @Test(priority = 24)
    public void checking_checkout_date() {

        given()
            .spec(requestSpec)
            .queryParam("checkin", "2022-01-01")
            .queryParam("checkout", "2023-06-01")
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }

    @Test(priority = 25)
    public void verifyBookingWithNonExistentFilterValue() {

        Response response =
                given()
                        .spec(requestSpec)
                        .queryParam("firstname", "XYZ123ABC")
                .when()
                        .get("/booking");

        // Status Code Validation
        response.then().statusCode(200);

        // Response should be an empty list
        response.then().body("size()", equalTo(0));
    }
}
