package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import base.BaseTest;

public class GetBookingByIdTest extends BaseTest {

    @Test(priority = 26)
    public void get_booking_id() {
System.out.println("Booking ID in Get = " + bookingId);

        given()
            .spec(requestSpec)
            .pathParam("id", bookingId)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(200);
    }

    @Test(priority = 27)
    public void verifyBookingResponseStructure() {

        given()
            .spec(requestSpec)
            .pathParam("id", bookingId)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(200)
            // Verify Response Fields
            .body("firstname", notNullValue())
            .body("lastname", notNullValue())
            .body("totalprice", notNullValue())
            .body("depositpaid", notNullValue())
            .body("bookingdates", notNullValue())
            .body("bookingdates.checkin", notNullValue())
            .body("bookingdates.checkout", notNullValue())
            .body("additionalneeds", notNullValue());
    }

    @Test(priority = 28)
    public void verify_non_existing_id() {
        given()
            .spec(requestSpec)
            .pathParam("id", 9999)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(404);
    }

    @Test(priority = 29)
    public void verify_booking_id_with_negative_number() {
        given()
            .spec(requestSpec)
            .pathParam("id", -99999)
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(404);
    }

    @Test(priority = 30)
    public void verify_booking_id_non_numeric_alphabetic() {
        given()
            .spec(requestSpec)
            .pathParam("id", "abcd")
        .when()
            .get("/booking/{id}")
        .then()
            .statusCode(404);
    }

    @Test(priority = 31)
    public void verify_booking_url_with_empty_parameter() {
        given()
            .spec(requestSpec)
        .when()
            .get("/booking")
        .then()
            .statusCode(200);
    }
}
