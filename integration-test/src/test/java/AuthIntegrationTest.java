import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
    @BeforeAll
    public static void setup() {
        // Setup code for authentication tests, e.g., initialize test users, mock services, etc.
        RestAssured.baseURI = "http://localhost:4004"; // Set the base URI for the API
    }

    @Test
    public void shouldGetOkWithValidToken(){
        String loginPayload = """
            {
                "email": "testuser@test.com",
                "password": "password123"
            }
        """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract().response();

        System.out.println("Received token: " + response.path("token"));
    }
    @Test
    public void shouldReturnUnauthorizeOnInvalidLogin(){
        String loginPayload = """
            {
                "email": "invaliduser@test.com",
                "password": "password123"
            }
        """;

        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);
    }
}
