package org.springframework.samples.petclinic.care;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cares")
public class Care extends BaseEntity{

    @NotNull
    @Column(name = "name", unique = true)
    @Length(min = 5, max = 30)
    String name;

    @NotNull
    @Column(name = "description")
    String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    @JoinTable(name = "compatible_pet_types", joinColumns = @JoinColumn(name = "type_id"), 
            inverseJoinColumns = @JoinColumn(name = "care_id"))
    Set<PetType> compatiblePetTypes;
}
