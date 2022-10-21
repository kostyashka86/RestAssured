package config;

import services.PetApi;
public class Config {
    public PetApi getPetApi() {
        return new PetApi();
    }
}
