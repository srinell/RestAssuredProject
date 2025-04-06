package nz.org.api.v1.account;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticateTest {
    public String token;
    public String credentialsJson = "{\n" +
            "  \"userName\": \"srinell\",\n" +
            "  \"password\": \"Test@123\"\n" +
            "}";
    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateTest.class);

    @Test
    public void authenticateUserWithPasswordTest() {
        given()
                .body(credentialsJson)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post("https://demoqa.com/Account/v1/Authorized")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(equalTo("true"));
    }

    @Test
    public void authenticateUserGenerateTokenTest() {
        Response resp = (Response) given()
                .body(credentialsJson)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post("https://demoqa.com/Account/v1/GenerateToken")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("status", equalTo("Success"))
                .assertThat().body("result", equalTo("User authorized successfully."))
                .extract().response();

        // Fetch token
        token = resp.path("token");
        System.out.println("Token: " + token);
        LOGGER.info("Token: " + token);
    }
}
