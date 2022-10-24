package pet.createPet;

import config.Config;
import dto.Pet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.PetApi;

public class CreatePetTest extends Config {

    private final PetApi petApi = new PetApi();

    @DataProvider
    public Object[][] petAndId() {
        return new Object[][]{
                {petApi.defaultPet, petApi.newPet, "111"},
                {petApi.defaultPetOnlyId, petApi.newPetOnlyId, "222"}};
    }

    @Test(dataProvider = "petAndId")
    public void testCheckCreateAndUpdatePet(Pet defaultPet, Pet newPet, String id) {
        petApi.createPetWithDefaultParams(defaultPet);
        petApi.petShouldBeSame(defaultPet, id);
        petApi.updateDefaultPet(newPet);
        petApi.petShouldBeSame(newPet, id);
    }
}
