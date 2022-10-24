package services;

import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET = "/pet";
    private final RequestSpecification spec;

    public PetApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse createPet(Pet pet) {
        return given(spec)
                            .basePath(PET)
                            .body(pet)
                .when()
                            .post()
                .then()
                            .log().all();
    }

    public ValidatableResponse getPetId(String id) {
        return given(spec)
                            .basePath(PET + "/" + id)
                .when()
                            .get()
                .then()
                            .log().all()
                            .statusCode(200);
    }
}
