package services;

import dto.Pet;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String PET = "/pet";
    private final RequestSpecification spec;

    public Pet defaultPet = Pet.builder()
            .id(111)
            .name("defaultName")
            .status("available")
            .build();

    public Pet newPet = Pet.builder()
            .id(111)
            .name("newName")
            .status("available")
            .build();

    public Pet defaultPetOnlyId = Pet.builder()
            .id(222)
            .build();

    public Pet newPetOnlyId = Pet.builder()
            .id(222)
            .name("nameWithOnlyId")
            .build();

    public PetApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public Pet getPetId(String id) {
        return given(spec)
                            .basePath(PET + "/" + id)
                .when()
                            .get()
                .then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .body()
                            .as(Pet.class);
    }

    public void createPetWithDefaultParams(Pet pet) {
        given(spec)
                .basePath(PET)
                .body(pet)
                .when()
                .post()
                .then()
                .log().all();
    }

    public void updateDefaultPet(Pet newPet) {
        given(spec)
                .basePath(PET)
                .body(newPet)
                .when()
                .post()
                .then()
                .log().all();
    }

    public void petShouldBeSame(Pet pet, String id) {
        assertEquals(pet, getPetId(id));
    }
}
