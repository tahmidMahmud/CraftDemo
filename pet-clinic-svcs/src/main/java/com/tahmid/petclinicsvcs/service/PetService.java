package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Pet;
import com.tahmid.petclinicsvcs.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> getAllPets() {
        return repository.findAll();
    }

    public Optional<Pet> getPetByID(Long id) {
        return this.repository.findById(id);
    }

    public Pet createPet(Pet newPet) {
        return this.repository.save(newPet);
    }

    public Optional<Pet> modifyPet(Long id, Pet newPet) {
        return this.repository.findById(id)
                .map(pet -> {
                    pet.setName(newPet.getName());
                    pet.setBirthDate(newPet.getBirthDate());
                    pet.setType(newPet.getType());
                    return this.repository.save(pet);
                });
    }

    public void deletePet(Long id) {
        this.repository.deleteById(id);
    }
}
