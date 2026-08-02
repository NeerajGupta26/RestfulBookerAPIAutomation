package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.BaseTest;

public class DeleteBookingTest extends BaseTest {

    @Test(priority = 46)
    public void delete_existing_booking() {
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
        .when()
                .delete("/booking/{id}")
        .then()
                .statusCode(201);
    }

    @Test(priority = 47)
    public void verify_deleted_booking_is_no_longer_accessible() {
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
        .when()
                .get("/booking/{id}")
        .then()
                .statusCode(404);
    }

    @Test(priority = 48)
    public void verify_delete_is_rejected_when_no_authentication_token_is_provided() {
        given()
                .spec(requestSpec)
                .pathParam("id", bookingId)
        .when()
                .delete("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 49)
    public void verify_delete_is_rejected_with_an_invalid_token() {
        given()
                .spec(requestSpec)
                .cookie("token", "tokeninvalid33")
                .pathParam("id", bookingId)
        .when()
                .delete("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 50)
    public void verify_delete_fails_for_a_non_existent_booking_ID_even_with_a_valid_token() {
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", 9999909)
        .when()
                .delete("/booking/{id}")
        .then()
                .statusCode(405);
    }
}
