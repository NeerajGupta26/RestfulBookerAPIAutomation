package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import base.BaseTest;
import static org.hamcrest.Matchers.equalTo;

public class PartialUpdateBookingTest extends BaseTest {

    @Test(priority = 41)
    public void verify_update_multiple_fields() {
        String json = """
                {
                "firstname": "Rahul",
                "totalprice": 800
                }
                """;
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(json)
        .when()
                .patch("/booking/{id}")
        .then()
                .statusCode(200);
    }

    @Test(priority = 42)
    public void verify_updated_fields() {
        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
        .when()
                .get("/booking/{id}")
        .then()
                .statusCode(200)
                .body("firstname", equalTo("Rahul"))
                .body("totalprice", equalTo(800));
    }

    @Test(priority = 43)
    public void verify_updation_without_auth() {
        String json = """
                {"firstname": "ram"}
                """;

        given()
                .spec(requestSpec)
                .pathParam("id", bookingId)
                .body(json)
        .when()
                .patch("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 44)
    public void verify_with_invalid_token_patch() {
        String json = """
                {"firstname": "ram"}
                """;

        given()
                .spec(requestSpec)
                .cookie("token", "tocan23444")
                .pathParam("id", bookingId)
                .body(json)
        .when()
                .patch("/booking/{id}")
        .then()
                .statusCode(403);
    }

    @Test(priority = 45)
    public void verify_with_depositpaid_invalid_strings() {
        String json = """
                {"depositpaid": "maybe"}
                """;

        given()
                .spec(requestSpec)
                .cookie("token", token)
                .pathParam("id", bookingId)
                .body(json)
        .when()
                .patch("/booking/{id}")
        .then()
                .statusCode(200);
    }
}
