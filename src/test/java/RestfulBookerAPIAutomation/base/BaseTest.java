package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected ConfigReader configReader;

protected static String token;
protected static int bookingId;

protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    @BeforeClass
    public void setup() {

        configReader = new ConfigReader();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .setContentType(ContentType.JSON)
                .build();
    }
}
