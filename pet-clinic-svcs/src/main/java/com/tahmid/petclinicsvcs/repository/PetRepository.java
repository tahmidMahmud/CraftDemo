package com.tahmid.petclinicsvcs.repository;

import com.tahmid.petclinicsvcs.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
