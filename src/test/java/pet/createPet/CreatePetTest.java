package pet.createPet;

import config.Config;
import dto.Pet;
import org.junit.jupiter.api.Test;
import services.PetApi;

import static org.hamcrest.Matchers.equalTo;

public class CreatePetTest extends Config {

    private final PetApi petApi = new PetApi();

    @Test
    public void checkCreateUser() {

        Pet pet = Pet.builder()
                .id(165)
                .name("FirstName")
                .status("available")
                .build();

        petApi.createPet(pet);

        //проверяем данные
        petApi.getPetId("165")
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo("FirstName"))
                .body("status", equalTo("available"));

        //обновляем данные
        Pet pet2 = Pet.builder()
                .id(165)
                .name("FirstName11")
                .status("available")
                .build();

        petApi.createPet(pet2);

        //проверяем обновление данных
        petApi.getPetId("165")
                .statusCode(200)
                .body("id", equalTo(165))
                .body("name", equalTo("FirstName11"))
                .body("status", equalTo("available"));


    }


        /*кейс: отправить запрос для обновления информации о питомце с одним параметром
        проверить: статус 200, отправленный id создался, неотправленные параметры - null.*/

    @Test
    public void petUpdateOnlyId(){
        Pet pet = Pet.builder()
                .id(555)
                .build();

        petApi.createPet(pet)
                .statusCode(200)
                .body("id", equalTo(555))
                .body("name", equalTo(null))
                .body("status", equalTo(null));

        //обновляем данные (другой id)
        Pet pet2 = Pet.builder()
                .id(777)
                .build();

        petApi.createPet(pet2);

        //проверяем обновление
        petApi.getPetId("777")
                .statusCode(200)
                .body("id", equalTo(777))
                .body("name", equalTo(null))
                .body("status", equalTo(null));
    }
}
