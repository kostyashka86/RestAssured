package pet.createPet;

import config.Config;
import dto.Pet;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import services.PetApi;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePetTest extends Config {

    private final PetApi petApi = new PetApi();


    public Object[][] petAndId() {
        return new Object[][]{
                {petApi.defaultPet, petApi.newPet, "111"},
                {petApi.defaultPetOnlyId, petApi.newPetOnlyId, "222"}};
    }

    @ParameterizedTest
    @MethodSource("petAndId")
    public void testCheckCreateAndUpdatePet(Pet defaultPet, Pet newPet, String id) {
        petApi.createPetWithDefaultParams(defaultPet);
        petApi.petShouldBeSame(defaultPet, id);
        petApi.updateDefaultPet(newPet);
        petApi.petShouldBeSame(newPet, id);
    }
}
