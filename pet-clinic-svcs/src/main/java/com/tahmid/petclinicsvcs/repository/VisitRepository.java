package com.tahmid.petclinicsvcs.repository;

import com.tahmid.petclinicsvcs.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v \n" +
            "FROM Visit v\n" +
            "WHERE ((v.startDate BETWEEN ?1 AND ?2) OR (v.endDate BETWEEN ?1 AND ?2))\n" +
            "AND (v.vet.id = ?3 OR v.pet.id = ?4)")
    Collection<Visit> findVisitConflict(LocalDateTime startDate, LocalDateTime endTime, Long petId, Long vetId);

    List<Visit> findByStartDateBetweenAndPetIdAndVetId(
            LocalDateTime startDate, LocalDateTime endTime, Long petId, Long vetId
    );
    List<Visit> findByEndDateBetweenAndPetIdAndVetId(
            LocalDateTime startDate, LocalDateTime endTime, Long petId, Long vetId
    );

}
