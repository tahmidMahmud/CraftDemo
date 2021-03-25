package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Vet;
import com.tahmid.petclinicsvcs.repository.VetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetService {

    private final VetRepository repository;

    public VetService(VetRepository repository) {
        this.repository = repository;
    }

    public List<Vet> getAllVets() {
        return repository.findAll();
    }

    public Optional<Vet> getVetByID(Long id) {
        return this.repository.findById(id);
    }

    public Vet createVet(Vet newVet) {
        return this.repository.save(newVet);
    }

    public Optional<Vet> modifyVet(Long id, Vet newVet) {
        return this.repository.findById(id)
                .map(vet -> {
                    vet.setFirstName(newVet.getFirstName());
                    vet.setLastName(newVet.getLastName());
                    vet.setSpecialties(newVet.getSpecialties());
                    return this.repository.save(vet);
                });
    }

    public void deleteVet(Long id) {
        this.repository.deleteById(id);
    }
}
