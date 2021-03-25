package com.tahmid.petclinicsvcs.controller;

import com.tahmid.petclinicsvcs.exception.PetClinicNotFoundException;
import com.tahmid.petclinicsvcs.model.Visit;
import com.tahmid.petclinicsvcs.service.VisitService;
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
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visits")
    public ResponseEntity<List<Visit>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }

    @GetMapping("/visits/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        return Optional.ofNullable(id)
                .flatMap(i -> visitService.getVisitByID(i))
                .map(visit -> ResponseEntity.ok(visit))
                .orElseThrow(() -> new PetClinicNotFoundException("Visit with : " + id + " was not found"));
    }

    @PostMapping("/visits")
    ResponseEntity<Visit> createVisit(@RequestBody Visit newVisit) {
        return ResponseEntity.ok(visitService.createVisit(newVisit));
    }

    @PutMapping("/visits/{id}")
    ResponseEntity<Visit> modifyVisit(@PathVariable Long id, @RequestBody Visit newVisit) {
        return Optional.ofNullable(id)
                .flatMap(i -> visitService.modifyVisit(id, newVisit))
                .map(visit -> ResponseEntity.ok(visit))
                .orElseThrow(() -> new PetClinicNotFoundException("Visit with : " + id + " was not found"));
    }

    @DeleteMapping("/visits/{id}")
    ResponseEntity<Visit> deleteVisit(@PathVariable Long id) {
        this.visitService.deleteVisit(id);
        return ResponseEntity.ok(null);
    }

}

