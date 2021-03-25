package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Vet;
import com.tahmid.petclinicsvcs.repository.VetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VetServiceTest {

    @Mock
    private VetRepository vetRepository;

    @InjectMocks
    VetService vetService;

    @Test
    public void canary() {
        Assert.assertTrue(true);
    }

    @Test
    public void happyPathGetAllBills() {
        //given a repository of vets
        List<Vet> expectedVets = Arrays.asList(new Vet(), new Vet(), new Vet());
        Mockito.when(vetRepository.findAll()).thenReturn(expectedVets);

        //when get all vets is called
        List<Vet> resultedVets = vetService.getAllVets();

        //then all vets in the repository are returned;
        Assert.assertEquals(expectedVets, resultedVets);
    }

    @Test
    public void happyPathGetBillById() {
        //given a repository with an vet of id 25
        Long id = 25L;
        Vet expectedVet = new Vet().toBuilder().id(id).build();
        Mockito.when(vetRepository.findById(id)).thenReturn(Optional.of(expectedVet));

        //when get by vets id is called
        Optional<Vet> resultedVets = vetService.getVetByID(id);

        //then vets in the repository with the correct id is returned;
        Assert.assertTrue(resultedVets.isPresent());
        Assert.assertEquals(resultedVets.get(), expectedVet);
        Assert.assertEquals(resultedVets.get().getId(), id);
    }

    @Test
    public void happyPathCreateVetTest() {
        //given a repository with an vet and a repository to add them
        Long generatedId = 23L;
        Vet expectedVet = new Vet().toBuilder()
                .firstName("John")
                .lastName("Doe")
                .specialties(Arrays.asList("Radiology", "Surgery"))
                .build();

        Mockito.when(vetRepository.save(expectedVet))
                .thenReturn(expectedVet.toBuilder().id(generatedId).build());

        //when create vet is called
        Vet resultedVets = vetService.createVet(expectedVet);

        //then vet that is saved to the repository with a generated id returned;
        Assert.assertEquals(resultedVets.getId(), generatedId);
    }

    @Test
    public void happyPathModifyVetTest() {
        //given a repository with an vet and id
        Long generatedId = 23L;
        Vet inputVet = new Vet().toBuilder()
                .firstName("John")
                .lastName("Doe")
                .specialties(Arrays.asList("Radiology", "Surgery"))
                .build();

        Vet modifiedVet = inputVet.toBuilder().id(generatedId).build();

        Mockito.when(vetRepository.findById(generatedId))
                .thenReturn(Optional.of(new Vet().toBuilder().id(generatedId).build()));
        Mockito.when(vetRepository.save(modifiedVet)).thenReturn(modifiedVet);


        //when modify vet is called
        Optional<Vet> resultedVets = vetService.modifyVet(generatedId, inputVet);

        //then vets in the repository with the correct id is returned;
        Assert.assertTrue(resultedVets.isPresent());
        Assert.assertEquals(resultedVets.get(), modifiedVet);
        Assert.assertEquals(resultedVets.get().getId(), generatedId);
    }

    @Test
    public void happyPathDeleteVetTest() {
        //given an id
        Long id = 23L;

        //when delete vet is called
        vetService.deleteVet(id);

        //then repository delete vet is called
        Mockito.verify(vetRepository, Mockito.times(1)).deleteById(id);
    }
}

