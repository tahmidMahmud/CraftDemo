package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.model.Owner;
import com.tahmid.petclinicsvcs.repository.OwnerRepository;
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
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    OwnerService ownerService;

    @Test
    public void canary() {
        Assert.assertTrue(true);
    }

    @Test
    public void happyPathGetAllBills() {
        //given a repository of owners
        List<Owner> expectedOwners = Arrays.asList(new Owner(), new Owner(), new Owner());
        Mockito.when(ownerRepository.findAll()).thenReturn(expectedOwners);

        //when get all owners is called
        List<Owner> resultedOwners = ownerService.getAllOwners();

        //then all owners in the repository are returned;
        Assert.assertEquals(expectedOwners, resultedOwners);
    }

    @Test
    public void happyPathGetBillById() {
        //given a repository with an owner of id 25
        Long id = 25L;
        Owner expectedOwner = new Owner().toBuilder().id(id).build();
        Mockito.when(ownerRepository.findById(id)).thenReturn(Optional.of(expectedOwner));

        //when get by owners id is called
        Optional<Owner> resultedOwners = ownerService.getOwnerById(id);

        //then owners in the repository with the correct id is returned;
        Assert.assertTrue(resultedOwners.isPresent());
        Assert.assertEquals(resultedOwners.get(), expectedOwner);
        Assert.assertEquals(resultedOwners.get().getId(), id);
    }

    @Test
    public void happyPathCreateOwnerTest() {
        //given a repository with an owner and a repository to add them
        Long generatedId = 23L;
        Owner expectedOwner = new Owner().toBuilder()
                .firstName("John")
                .lastName("Doe")
                .address("123 New York")
                .city("New York")
                .email("John.Doe@gmail.com")
                .telephone("1231231234")
                .build();

        Mockito.when(ownerRepository.save(expectedOwner))
                .thenReturn(expectedOwner.toBuilder().id(generatedId).build());

        //when create owner is called
        Owner resultedOwners = ownerService.createOwner(expectedOwner);

        //then owner that is saved to the repository with a generated id returned;
        Assert.assertEquals(resultedOwners.getId(), generatedId);
    }

    @Test
    public void happyPathModifyOwnerTest() {
        //given a repository with an owner and id
        Long generatedId = 23L;
        Owner inputOwner = new Owner().toBuilder()
                .firstName("John")
                .lastName("Doe")
                .address("123 New York")
                .city("New York")
                .email("John.Doe@gmail.com")
                .telephone("1231231234")
                .build();

        Owner modifiedOwner = inputOwner.toBuilder().id(generatedId).build();

        Mockito.when(ownerRepository.findById(generatedId))
                .thenReturn(Optional.of(new Owner().toBuilder().id(generatedId).build()));
        Mockito.when(ownerRepository.save(modifiedOwner)).thenReturn(modifiedOwner);


        //when modify owner is called
        Optional<Owner> resultedOwners = ownerService.modifyOwner(generatedId, inputOwner);

        //then owners in the repository with the correct id is returned;
        Assert.assertTrue(resultedOwners.isPresent());
        Assert.assertEquals(resultedOwners.get(), modifiedOwner);
        Assert.assertEquals(resultedOwners.get().getId(), generatedId);
    }

    @Test
    public void happyPathDeleteOwnerTest() {
        //given an id
        Long id = 23L;

        //when delete owner is called
        ownerService.deleteOwner(id);

        //then repository delete owner is called
        Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(id);
    }
}
