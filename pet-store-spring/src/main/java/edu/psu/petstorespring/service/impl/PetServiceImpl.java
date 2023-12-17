package edu.psu.petstorespring.service.impl;

import edu.psu.petstorespring.model.Pet;
import edu.psu.petstorespring.repository.PetRepository;
import edu.psu.petstorespring.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public ArrayList<Pet> getPets(){return petRepository.getPets();}

    @Override
    public void addPet(String name, String type, Double weight, Integer age){
        var newPet = new Pet(null, name, type, weight, age);
        petRepository.addPet(newPet);
    }

    @Override
    public List<Pet> getFilteredWatches(String search) {
        if (!StringUtils.hasText(search)) {
            throw new IllegalArgumentException("search is required");
        }
        return petRepository.getFilteredWatches(search);
    }

    @Override
    public Pet getPetById(Long petId) {
        return petRepository.getPetById(petId);
    }

    @Override
    public void editPet(String name, String type, Double weight, Integer age, Long petId) {
        petRepository.editPet(name, type, weight, age, petId);
    }

    @Override
    public void deletePet(Long petId) {
        if (petId == null || petId <= 0) {
            throw new IllegalArgumentException("pet ID is required");
        }
        petRepository.deletePet(petId);
    }
}
