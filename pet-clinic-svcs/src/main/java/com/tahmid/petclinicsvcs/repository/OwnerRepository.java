package com.tahmid.petclinicsvcs.repository;

import com.tahmid.petclinicsvcs.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
