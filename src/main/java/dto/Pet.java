package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class Pet {
    public int id;
    public String name;
    public String status;
    public Category category;
    public ArrayList<String> photoUrls;
    public ArrayList<Tag> tags;

    public Pet() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(name, pet.name) && Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}
