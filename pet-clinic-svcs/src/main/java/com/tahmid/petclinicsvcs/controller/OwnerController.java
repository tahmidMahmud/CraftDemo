package com.tahmid.petclinicsvcs.controller;

import com.tahmid.petclinicsvcs.exception.PetClinicNotFoundException;
import com.tahmid.petclinicsvcs.model.Owner;
import com.tahmid.petclinicsvcs.service.OwnerService;
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
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .flatMap(i -> ownerService.getOwnerById(i))
                .map(owner -> ResponseEntity.ok(owner))
                .orElseThrow(() -> new PetClinicNotFoundException("Owner with : " + id + " was not found"));
    }

    @PostMapping("/owners")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner newOwner) {
        return ResponseEntity.ok(ownerService.createOwner(newOwner));
    }

    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> modifyOwner(@PathVariable Long id, @RequestBody Owner newOwner) {
        return Optional.ofNullable(id)
                .flatMap(i -> ownerService.modifyOwner(id, newOwner))
                .map(owner -> ResponseEntity.ok(owner))
                .orElseThrow(() -> new PetClinicNotFoundException("Owner with : " + id + " was not found"));
    }

    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Owner> deleteOwner(@PathVariable Long id) {
        this.ownerService.deleteOwner(id);
        return ResponseEntity.ok(null);
    }

}
