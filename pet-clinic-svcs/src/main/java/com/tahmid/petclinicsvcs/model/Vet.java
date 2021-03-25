package com.tahmid.petclinicsvcs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
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
public class Vet {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String firstName;

    private String lastName;

    @ElementCollection
    private List<String> specialties;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vet")
    private List<Visit> visit;

}
