package org.springframework.samples.petclinic.care;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CareService {    

    private CareProvisionRepository careProvisionRepository;

    public List<Care> getAllCares(){
        return null;
    }

    public List<Care> getAllCompatibleCares(String petTypeName){
        return null;
    }

    public Care getCare(String careName) {
        return careProvisionRepository.findCareByName(careName);
    }

    @Transactional(rollbackFor = {NonCompatibleCaresException.class, UnfeasibleCareException.class})
    public CareProvision save(CareProvision p) throws NonCompatibleCaresException, UnfeasibleCareException {
        if(!p.getCare().getIncompatibleCares().contains(p.getCare())) {
            throw new NonCompatibleCaresException();
        } else if (p.getCare().getCompatiblePetTypes().contains(p.getVisit().getPet().getType())){
            throw new UnfeasibleCareException();
        } 
        
        else {
            careProvisionRepository.save(p);
            return p;
        }
    }

    public List<CareProvision> getAllCaresProvided(){
        return careProvisionRepository.findAll();
    }

    public List<CareProvision> getCaresProvided(Integer visitId){
        return careProvisionRepository.findCaresProvidedByVisitId(visitId);

    }
    
}
