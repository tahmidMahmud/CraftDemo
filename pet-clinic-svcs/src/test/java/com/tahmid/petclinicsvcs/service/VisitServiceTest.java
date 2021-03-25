package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Pet;
import com.tahmid.petclinicsvcs.model.Vet;
import com.tahmid.petclinicsvcs.model.Visit;
import com.tahmid.petclinicsvcs.repository.VisitRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VisitServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    VisitService visitService;

    @Test
    public void canary() {
        Assert.assertTrue(true);
    }

    @Test
    public void happyPathGetAllBills() {
        //given a repository of visits
        List<Visit> expectedVisits = Arrays.asList(new Visit(), new Visit(), new Visit());
        Mockito.when(visitRepository.findAll()).thenReturn(expectedVisits);

        //when get all visits is called
        List<Visit> resultedVisits = visitService.getAllVisits();

        //then all visits in the repository are returned;
        Assert.assertEquals(expectedVisits, resultedVisits);
    }

    @Test
    public void happyPathGetBillById() {
        //given a repository with an visit of id 25
        Long id = 25L;
        Visit expectedVisit = new Visit().toBuilder().id(id).build();
        Mockito.when(visitRepository.findById(id)).thenReturn(Optional.of(expectedVisit));

        //when get by visits id is called
        Optional<Visit> resultedVisits = visitService.getVisitByID(id);

        //then visits in the repository with the correct id is returned;
        Assert.assertTrue(resultedVisits.isPresent());
        Assert.assertEquals(resultedVisits.get(), expectedVisit);
        Assert.assertEquals(resultedVisits.get().getId(), id);
    }

    @Test
    public void happyPathCreateVisitTest() {
        //given a repository with an visit and a repository to add them
        Long generatedId = 23L;
        Vet vet = new Vet().toBuilder().id(12L).build();
        Pet pet = new Pet().toBuilder().id(12L).build();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        Visit expectedVisit = new Visit().toBuilder()
                .vet(vet)
                .pet(pet)
                .description("surgery")
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Mockito.when(visitRepository.save(expectedVisit))
                .thenReturn(expectedVisit.toBuilder().id(generatedId).build());

        //when create visit is called
        Visit resultedVisits = visitService.createVisit(expectedVisit);

        //then visit that is saved to the repository with a generated id returned;
        Assert.assertEquals(resultedVisits.getId(), generatedId);
    }

    @Test
    public void happyPathModifyVisitTest() {
        //given a repository with an visit and id
        Long generatedId = 23L;
        Vet vet = new Vet().toBuilder().id(12L).build();
        Pet pet = new Pet().toBuilder().id(12L).build();

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        Visit inputVisit = new Visit().toBuilder()
                .vet(vet)
                .pet(pet)
                .description("surgery")
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Visit modifiedVisit = inputVisit.toBuilder().id(generatedId).build();
        Mockito.when(visitRepository.findById(generatedId))
                .thenReturn(Optional.of(new Visit().toBuilder().id(generatedId).build()));
        Mockito.when(visitRepository.save(modifiedVisit)).thenReturn(modifiedVisit);


        //when modify visit is called
        Optional<Visit> resultedVisits = visitService.modifyVisit(generatedId, inputVisit);

        //then visits in the repository with the correct id is returned;
        Assert.assertTrue(resultedVisits.isPresent());
        Assert.assertEquals(resultedVisits.get(), modifiedVisit);
        Assert.assertEquals(resultedVisits.get().getId(), generatedId);
    }

    @Test
    public void happyPathDeleteVisitTest() {
        //given an id
        Long id = 23L;

        //when delete visit is called
        visitService.deleteVisit(id);

        //then repository delete visit is called
        Mockito.verify(visitRepository, Mockito.times(1)).deleteById(id);
    }
}
