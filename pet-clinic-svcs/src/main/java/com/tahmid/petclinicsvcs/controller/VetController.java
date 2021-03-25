package com.tahmid.petclinicsvcs.controller;

import com.tahmid.petclinicsvcs.exception.PetClinicNotFoundException;
import com.tahmid.petclinicsvcs.model.Vet;
import com.tahmid.petclinicsvcs.service.VetService;
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
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/vets")
    public ResponseEntity<List<Vet>> getAllVets() {
        return ResponseEntity.ok(vetService.getAllVets());
    }

    @GetMapping("/vets/{id}")
    public ResponseEntity<Vet> getVetById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .flatMap(i -> vetService.getVetByID(i))
                .map(vet -> ResponseEntity.ok(vet))
                .orElseThrow(() -> new PetClinicNotFoundException("Vet with : " + id + " was not found"));
    }

    @PostMapping("/vets")
    ResponseEntity<Vet> createVet(@RequestBody Vet newVet) {
        return ResponseEntity.ok(vetService.createVet(newVet));
    }

    @PutMapping("/vets/{id}")
    ResponseEntity<Vet> modifyVet(@PathVariable Long id, @RequestBody Vet newVet) {
        return Optional.ofNullable(id)
                .flatMap(i -> vetService.modifyVet(id, newVet))
                .map(vet -> ResponseEntity.ok(vet))
                .orElseThrow(() -> new PetClinicNotFoundException("Vet with : " + id + " was not found"));
    }

    @DeleteMapping("/vets/{id}")
    ResponseEntity<Vet> deleteVet(@PathVariable Long id) {
        this.vetService.deleteVet(id);
        return ResponseEntity.ok(null);
    }

}
