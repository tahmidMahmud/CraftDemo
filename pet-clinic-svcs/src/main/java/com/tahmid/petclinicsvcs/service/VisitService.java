package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.exception.PetClinicVisitConflictException;
import com.tahmid.petclinicsvcs.model.Visit;
import com.tahmid.petclinicsvcs.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository repository;

    public VisitService(VisitRepository repository) {
        this.repository = repository;
    }

    public List<Visit> getAllVisits() {
        return repository.findAll();
    }

    public Optional<Visit> getVisitByID(Long id) {
        return this.repository.findById(id);
    }

    public Visit createVisit(Visit newVisit) {
        Collection<Visit> conflict = repository.findVisitConflict(newVisit.getStartDate(), newVisit.getEndDate(),
                newVisit.getVet().getId(), newVisit.getPet().getId());
        if (!conflict.isEmpty()) {
            throw new PetClinicVisitConflictException();
        }
        return this.repository.save(newVisit);
    }

    public Optional<Visit> modifyVisit(Long id, Visit newVisit) {
        return this.repository.findById(id)
                .map(visit -> {
                    visit.setVet(newVisit.getVet());
                    visit.setPet(newVisit.getPet());
                    visit.setStartDate(newVisit.getStartDate());
                    visit.setEndDate(newVisit.getEndDate());
                    visit.setDescription(newVisit.getDescription());
                    return this.repository.save(visit);
                });
    }

    public void deleteVisit(Long id) {
        this.repository.deleteById(id);
    }
}
