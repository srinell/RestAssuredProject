package nz.org.api.v1.bookstore;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class CRUDTest {
    @Test
    public void getBooks() {
        given()
            .header("accept", "application/json")
        .when()
            .get("https://demoqa.com/BookStore/v1/Books")
        .then()
            .assertThat().statusCode(200);
    }

    @Test(enabled = false)
    public void postBooks() {
        String json = "{\n" +
                "  \"userId\": \"srinell\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \"Test123\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        given()
                .body(json)
                .header("Content-Type", "application/json")
                .when()
                .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                .assertThat().statusCode(201);
    }

    @Test(enabled = false)
    public void deleteBooks() {
        given()
                .pathParam("UserId", "srinell")
                .header("Accept", "*/*")
                .when()
                .delete("https://demoqa.com/BookStore/v1/Books")
                .then()
                .assertThat().statusCode(204)
                .assertThat().body(equalTo("Books deleted"));
    }
}
