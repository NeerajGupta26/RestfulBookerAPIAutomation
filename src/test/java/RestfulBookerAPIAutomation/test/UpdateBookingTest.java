package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.BaseTest;
import java.io.File;

import static org.hamcrest.Matchers.equalTo;

public class UpdateBookingTest extends BaseTest {

    File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking.json");
    File withoutFirstname = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_without_firstname.json");
    File jsonNumber = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_with_totalprice_negative_number.json");

    @Test(priority = 32)
    public void verifyUpdateBooking() {

System.out.println("Booking ID in Update = " + bookingId);
System.out.println("Token in Update = " + token);
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(jsonFile)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(200);
    }

    @Test(priority = 33)
    public void verifyUpdatedBookingDataPersists() {

        given()
                .spec(requestSpec)
                .pathParam("id", bookingId)
        .when()
                .get("/booking/{id}")
        .then()
                .statusCode(200)
                .body("firstname", equalTo("Neeraj"))
                .body("lastname", equalTo("Gupta"))
                .body("totalprice", equalTo(500))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2026-08-01"))
                .body("bookingdates.checkout", equalTo("2026-08-01"))
                .body("additionalneeds", equalTo("Breakfast"));
    }

    @Test(priority = 34)
    public void verify_booking_updation_without_auth() {

        given()
                .spec(requestSpec)
                .pathParam("id", bookingId)
                .body(jsonFile)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 35)
    public void verify_booking_updation_invalid_token() {

        given()
                .spec(requestSpec)
                .pathParam("id", bookingId)
                .cookie("token", "invalidToken123")
                .body(jsonFile)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 36)
    public void verify_with_valid_token_non_existing_id() {

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", 999999)
                .body(jsonFile)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(405);
    }

    @Test(priority = 37)
    public void verify_missing_field() {

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(withoutFirstname)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(400);
    }

    @Test(priority = 38)
    public void verify_empty_body() {

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body("{}")
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(400);
    }

    @Test(priority = 39)
    public void verify_with_totalprice_non_number() {

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(jsonNumber)
        .when()
                .put("/booking/{id}")
        .then()
                .statusCode(200);
    }

    @Test(priority = 40)
    public void verify_with_change_name_field() {
        String changeName = """
                {
                "firstname": "Laptop"
                }
                """;

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(changeName)
        .when()
                .patch("/booking/{id}")
        .then()
                .statusCode(200);
    }
}
