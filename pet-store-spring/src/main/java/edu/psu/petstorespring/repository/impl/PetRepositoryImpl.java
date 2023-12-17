package edu.psu.petstorespring.repository.impl;

import edu.psu.petstorespring.model.Pet;
import edu.psu.petstorespring.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.google.common.collect.MoreCollectors;

@Repository
public class PetRepositoryImpl implements PetRepository {

    private ArrayList<Pet> petList = new ArrayList<>();

    @Override
    public ArrayList<Pet> getPets(){return petList;}

    @Override
    public void addPet(Pet pet){
        if (pet.id() == null) {
            var largestWatchId = petList.stream().map(Pet::id).mapToInt(Long::intValue).max();
            if (largestWatchId.isEmpty()) {
                pet = new Pet((long) 1, pet.name(), pet.type(), pet.weight(), pet.age());
            } else {
                pet = new Pet((long) (largestWatchId.getAsInt() + 1), pet.name(), pet.type(), pet.weight(), pet.age());
            }
        }
        petList.add(pet);
    }

    @Override
    public List<Pet> getFilteredWatches(String search) {
        return petList.stream()
                .filter(w -> w.type().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Pet getPetById(Long petId) {
        return petList.stream().filter(w -> Objects.equals(w.id(), petId)).collect(MoreCollectors.onlyElement());
    }

    @Override
    public void editPet(String name, String type, Double weight, Integer age, Long petId) {
        var newPet = new Pet(petId, name, type, weight, age);
        for (var i = 0; i < petList.size(); i++) {
            if (Objects.equals(petList.get(i).id(), petId)) {
                petList.set(i, newPet);
            }
        }
    }

    @Override
    public void deletePet(Long petId) {
        petList = (ArrayList<Pet>) petList.stream().filter(w -> !Objects.equals(w.id(), petId)).collect(Collectors.toList());
    }

}
