package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Pet;
import com.tahmid.petclinicsvcs.repository.PetRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    PetService petService;

    @Test
    public void canary() {
        Assert.assertTrue(true);
    }

    @Test
    public void happyPathGetAllBills() {
        //given a repository of pets
        List<Pet> expectedPets = Arrays.asList(new Pet(), new Pet(), new Pet());
        Mockito.when(petRepository.findAll()).thenReturn(expectedPets);

        //when get all pets is called
        List<Pet> resultedPets = petService.getAllPets();

        //then all pets in the repository are returned;
        Assert.assertEquals(expectedPets, resultedPets);
    }

    @Test
    public void happyPathGetBillById() {
        //given a repository with an pet of id 25
        Long id = 25L;
        Pet expectedPet = new Pet().toBuilder().id(id).build();
        Mockito.when(petRepository.findById(id)).thenReturn(Optional.of(expectedPet));

        //when get by pets id is called
        Optional<Pet> resultedPets = petService.getPetByID(id);

        //then pets in the repository with the correct id is returned;
        Assert.assertTrue(resultedPets.isPresent());
        Assert.assertEquals(resultedPets.get(), expectedPet);
        Assert.assertEquals(resultedPets.get().getId(), id);
    }

    @Test
    public void happyPathCreatePetTest() {
        //given a repository with an pet and a repository to add them
        Long generatedId = 23L;
        Pet expectedPet = new Pet().toBuilder()
                .name("Fluffy")
                .birthDate(LocalDate.of(2019, 11, 11))
                .type("cat")
                .build();

        Mockito.when(petRepository.save(expectedPet))
                .thenReturn(expectedPet.toBuilder().id(generatedId).build());

        //when create pet is called
        Pet resultedPets = petService.createPet(expectedPet);

        //then pet that is saved to the repository with a generated id returned;
        Assert.assertEquals(resultedPets.getId(), generatedId);
    }

    @Test
    public void happyPathModifyPetTest() {
        //given a repository with an pet and id
        Long generatedId = 23L;
        Pet inputPet = new Pet().toBuilder()
                .name("Fluffy")
                .birthDate(LocalDate.of(2019, 11, 11))
                .type("cat")
                .build();

        Pet modifiedPet = inputPet.toBuilder().id(generatedId).build();

        Mockito.when(petRepository.findById(generatedId))
                .thenReturn(Optional.of(new Pet().toBuilder().id(generatedId).build()));
        Mockito.when(petRepository.save(modifiedPet)).thenReturn(modifiedPet);


        //when modify pet is called
        Optional<Pet> resultedPets = petService.modifyPet(generatedId, inputPet);

        //then pets in the repository with the correct id is returned;
        Assert.assertTrue(resultedPets.isPresent());
        Assert.assertEquals(resultedPets.get(), modifiedPet);
        Assert.assertEquals(resultedPets.get().getId(), generatedId);
    }

    @Test
    public void happyPathDeletePetTest() {
        //given an id
        Long id = 23L;

        //when delete pet is called
        petService.deletePet(id);

        //then repository delete pet is called
        Mockito.verify(petRepository, Mockito.times(1)).deleteById(id);
    }
}
