package com.tahmid.petclinicsvcs.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Builder(toBuilder = true, builderMethodName = "")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String email;

    private String telephone;

    @JsonManagedReference(value = "owner-pet")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Pet> pets;

}
