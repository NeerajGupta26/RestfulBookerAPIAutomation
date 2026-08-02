package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.BaseTest;
import java.io.File;
import io.restassured.response.Response;

public class CreatBookingTest extends BaseTest {

    @Test(priority = 10)
    public void createBooking() {

        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking.json");

        Response response =
                given()
                        .spec(requestSpec)
                        .body(jsonFile)
                .when()
                        .post("/booking");

        response.then()
                .statusCode(200);

        bookingId = response.jsonPath().getInt("bookingid");
System.out.println("Created Booking ID = " + bookingId);

    }

    @Test(priority = 11)
    public void creat_booking_without_aditional_fields() {
        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_without_additional_fields.json");
        given()
                .spec(requestSpec)
                .body(jsonFile)
                .when()
                .post("/booking")
                .then()
                .statusCode(400);
    }

    @Test(priority = 12)
    public void creating_booking_without_fristname() {
        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_without_firstname.json");
        given()
                .spec(requestSpec)
                .body(jsonFile)
                .when()
                .post("/booking")
                .then()
                .statusCode(500);
    }

    @Test(priority = 13)
    public void creating_booking_without_lastname() {
        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_without_lastname.json");
        given()
                .spec(requestSpec)
                .body(jsonFile)
                .when()
                .post("/booking")
                .then()
                .statusCode(500);
    }

    @Test(priority = 14)
    public void creating_booking_without_date_object() {
        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_without_date_object.json");
        given()
                .spec(requestSpec)
                .body(jsonFile)
                .when()
                .post("/booking")
                .then()
                .statusCode(500);
    }

    @Test(priority = 15)
    public void booking_with_totalprice_negative_number() {
        File jsonFile = new File("src/test/java/RestfulBookerAPIAutomation/payload/booking_with_totalprice_negative_number.json");
        given()
                .spec(requestSpec)
                .body(jsonFile)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }

    @Test(priority = 16)
    public void booking_with_totalprice_as_string() {
        String json = """
                {
                "firstname": "Neeraj",
                "lastname": "Gupta",
                "totalprice": "hello",
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2026-08-01",
                    "checkout": "2026-08-05"
                },
                "additionalneeds": "Breakfast"
                }
                """;
        given()
                .spec(requestSpec)
                .body(json)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }

    @Test(priority = 17)
    public void booking_depositpaid_as_string() {
        String json = """
                {
                "firstname": "Neeraj",
                "lastname": "Gupta",
                "totalprice": 500,
                "depositpaid": "yes",
                "bookingdates": {
                    "checkin": "2026-08-01",
                    "checkout": "2026-08-05"
                },
                "additionalneeds": "Breakfast"
                }
                """;
        given()
                .spec(requestSpec)
                .body(json)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }

    @Test(priority = 18)
    public void booking_dates() {
        String json = """
                {
                "firstname": "Neeraj",
                "lastname": "Gupta",
                "totalprice": 500,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2026-08-06",
                    "checkout": "2026-08-01"
                },
                "additionalneeds": "Breakfast"
                }
                """;
        given()
                .spec(requestSpec)
                .body(json)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }

    @Test(priority = 19)
    public void booking_with_special_characters_long_string() {
        String json = """
                {
                "firstname": "Neeraj_g@*@^%!~heloteststring",
                "lastname": "Gupta@!@*^#~gsui",
                "totalprice": 500,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2026-08-06",
                    "checkout": "2026-08-01"
                },
                "additionalneeds": "Breakfast"
                }
                """;
        given()
                .spec(requestSpec)
                .body(json)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }
}
