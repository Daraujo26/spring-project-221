package edu.psu.petstorespring.service;

import edu.psu.petstorespring.model.Pet;

import java.util.ArrayList;
import java.util.List;

public interface PetService {

    ArrayList<Pet> getPets();

    void addPet(String name, String type, Double weight, Integer age);

    List<Pet> getFilteredWatches(String searchParam);

    Pet getPetById(Long petId);

    void editPet(String name, String type, Double weight, Integer age, Long petId);

    void deletePet(Long petId);
}
