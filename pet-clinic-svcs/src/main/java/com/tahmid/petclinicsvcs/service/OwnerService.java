package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Owner;
import com.tahmid.petclinicsvcs.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<Owner> getAllOwners() {
        return this.repository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return this.repository.findById(id);
    }

    public Owner createOwner(Owner owner) {
        return this.repository.save(owner);
    }

    public Optional<Owner> modifyOwner(Long id, Owner newOwner) {
        return this.repository.findById(id)
                .map(owner -> {
                    owner.setFirstName(newOwner.getFirstName());
                    owner.setLastName(newOwner.getLastName());
                    owner.setAddress(newOwner.getAddress());
                    owner.setCity(newOwner.getCity());
                    owner.setEmail(newOwner.getEmail());
                    owner.setTelephone(newOwner.getTelephone());
                    owner.setPets(newOwner.getPets());
                    return this.repository.save(owner);
                });
    }

    public void deleteOwner(Long id) {
        this.repository.deleteById(id);
    }
}
