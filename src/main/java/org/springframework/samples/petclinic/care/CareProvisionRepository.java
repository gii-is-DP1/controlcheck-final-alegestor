package org.springframework.samples.petclinic.care;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CareProvisionRepository extends CrudRepository<CareProvision, Integer> {
    
    @Query("SELECT cp FROM CareProvision cp")
    List<CareProvision> findAll();        

    Optional<CareProvision> findById(int id);
    
    CareProvision save(CareProvision p);
	
    @Query("SELECT c FROM Care c")
    List<Care> findAllCares();

    @Query("SELECT c FROM Care c JOIN c.compatiblePetTypes cpt WHERE cpt.name = :petTypeName")
    List<Care> findCompatibleCares(String petTypeName);

    // Care findCareByName(String name);

    @Query("SELECT cp FROM CareProvision cp WHERE cp.visit.id = :visitId")
    List<CareProvision> getCaresPerformed(Integer visitId);
}
