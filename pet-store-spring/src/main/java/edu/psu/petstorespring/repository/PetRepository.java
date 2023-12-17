package edu.psu.petstorespring.repository;

import edu.psu.petstorespring.model.Pet;

import java.util.ArrayList;
import java.util.List;

public interface PetRepository {

    ArrayList<Pet> getPets();

    void addPet(Pet pet);

    List<Pet> getFilteredWatches(String search);

    Pet getPetById(Long petId);

    void editPet(String name, String type, Double weight, Integer age, Long petId);

    void deletePet(Long petId);
}
