package org.springframework.samples.petclinic.care;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CareProvisionRepository extends CrudRepository<CareProvision, Integer>{
    @Query("SELECT cprovision FROM CareProvision cprovision")
    List<CareProvision> findAll();        

    Optional<CareProvision> findById(int id);
    
    CareProvision save(CareProvision p);
	
    @Query("SELECT care FROM Care care")
    List<Care> findAllCares();

    @Query("SELECT care FROM Care care WHERE PetType.name := petTypeName AND care.compatiblePetTypes := PetType.id")
    List<Care> findCompatibleCares(String petTypeName);

    Care findCareByName(String name);

    @Query("SELECT care FROM Care care WHERE care.visitId := visitId")
    List<CareProvision> findCaresProvidedByVisitId(Integer visitId);
}
