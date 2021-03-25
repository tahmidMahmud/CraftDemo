package com.tahmid.petclinicsvcs.service;

import com.tahmid.petclinicsvcs.controller.OwnerController;
import com.tahmid.petclinicsvcs.model.Owner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Test
    public void canary() {
        Assert.assertTrue(true);
    }

    @Test
    public void happyPathGetAllBills() {
        //given a repository of owners
        List<Owner> expectedOwners = Arrays.asList(new Owner(), new Owner(), new Owner());
        Mockito.when(ownerService.getAllOwners()).thenReturn(expectedOwners);

        //when get all owners is called
        ResponseEntity<List<Owner>> resultedOwners = ownerController.getAllOwners();

        //then all owners in the repository are returned;
        Assert.assertEquals(expectedOwners, resultedOwners.getBody());
    }

    @Test
    public void happyPathGetBillById() {
        //given a repository with an owner of id 25
        Long id = 25L;
        Owner expectedOwner = new Owner().toBuilder().id(id).build();
        Mockito.when(ownerService.getOwnerById(id)).thenReturn(Optional.of(expectedOwner));

        //when get by owners id is called
        ResponseEntity<Owner> resultedOwners = ownerController.getOwnerById(id);

        //then owners in the repository with the correct id is returned;
        Assert.assertEquals(resultedOwners.getBody(), expectedOwner);
        Assert.assertEquals(resultedOwners.getBody().getId(), id);
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

        Mockito.when(ownerService.createOwner(expectedOwner))
                .thenReturn(expectedOwner.toBuilder().id(generatedId).build());

        //when create owner is called
        ResponseEntity<Owner> resultedOwners = ownerController.createOwner(expectedOwner);

        //then owner that is saved to the repository with a generated id returned;
        Assert.assertEquals(resultedOwners.getBody().getId(), generatedId);
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

        Mockito.when(ownerService.modifyOwner(generatedId, inputOwner))
                .thenReturn(Optional.of(inputOwner.toBuilder().id(generatedId).build()));


        //when modify owner is called
        ResponseEntity<Owner> resultedOwners = ownerController.modifyOwner(generatedId, inputOwner);

        //then owners in the repository with the correct id is returned;
        Assert.assertEquals(resultedOwners.getBody(), modifiedOwner);
        Assert.assertEquals(resultedOwners.getBody().getId(), generatedId);
    }

    @Test
    public void happyPathDeleteOwnerTest() {
        //given an id
        Long id = 23L;

        //when delete owner is called
        ownerController.deleteOwner(id);

        //then service delete owner is called
        Mockito.verify(ownerService, Mockito.times(1)).deleteOwner(id);
    }
}
