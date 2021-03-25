package com.tahmid.petclinicsvcs.controller;

import com.tahmid.petclinicsvcs.exception.PetClinicNotFoundException;
import com.tahmid.petclinicsvcs.model.Pet;
import com.tahmid.petclinicsvcs.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .flatMap(i -> petService.getPetByID(i))
                .map(pet -> ResponseEntity.ok(pet))
                .orElseThrow(() -> new PetClinicNotFoundException("Pet with : " + id + " was not found"));
    }

    @PostMapping("/pets")
    ResponseEntity<Pet> createPet(@RequestBody Pet newPet) {
        return ResponseEntity.ok(petService.createPet(newPet));
    }

    @PutMapping("/pets/{id}")
    ResponseEntity<Pet> modifyPet(@PathVariable Long id, @RequestBody Pet newPet) {
        return Optional.ofNullable(id)
                .flatMap(i -> petService.modifyPet(id, newPet))
                .map(pet -> ResponseEntity.ok(pet))
                .orElseThrow(() -> new PetClinicNotFoundException("Pet with : " + id + " was not found"));
    }

    @DeleteMapping("/pets/{id}")
    ResponseEntity<Pet> deletePet(@PathVariable Long id) {
        this.petService.deletePet(id);
        return ResponseEntity.ok(null);
    }

}
