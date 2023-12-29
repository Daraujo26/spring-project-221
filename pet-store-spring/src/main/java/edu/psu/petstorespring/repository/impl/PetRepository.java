package edu.psu.petstorespring.repository.impl;

import edu.psu.petstorespring.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // Custom query method
    List<Pet> findByTypeContaining(String type);
}

