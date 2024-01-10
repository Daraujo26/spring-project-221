package edu.psu.petstorespring.service.impl;

import edu.psu.petstorespring.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.psu.petstorespring.repository.impl.PetRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    public void addPet(Pet pet, MultipartFile imageFile) {
        try {
            String imageUrl = storeImage(imageFile);
            
            if (imageUrl != null) {
                pet.setImageUrl(imageUrl);
                petRepository.save(pet);
            } else {
                throw new RuntimeException("Failed to store image. Image URL is null.");
            }
        } catch (RuntimeException e) {
            System.err.println("Exception occurred: " + e.getMessage());
        }
    }
    

    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public List<Pet> searchPetsByType(String type) {
        return petRepository.findByTypeContaining(type);
    }

    public void editPet(String name, String type, Double weight, Integer age, Long petId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();

            pet.setName(name);
            pet.setType(type);
            pet.setWeight(weight);
            pet.setAge(age);

            petRepository.save(pet);
        } else {
            throw new RuntimeException("Pet not found with id: " + petId);
        }
    }

    private String storeImage(MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                if (file.getSize() > MAX_FILE_SIZE) {
                    throw new RuntimeException("File size exceeds the maximum limit");
                }
    
                String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                Path uploadDirectory = Paths.get("/var/spring-project-221/pet-store-spring/petstore/uploads");
    
                if (!Files.exists(uploadDirectory)) {
                    Files.createDirectories(uploadDirectory);
                }
    
                Path storagePath = uploadDirectory.resolve(filename);
                Files.copy(file.getInputStream(), storagePath, StandardCopyOption.REPLACE_EXISTING);
    
                return "/uploads/" + filename;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
        return null;
    }    
}
