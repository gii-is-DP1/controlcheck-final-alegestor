package org.springframework.samples.petclinic.care;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareService {    

    @Autowired
    private CareProvisionRepository careProvisionRepository;

    @Autowired
    public CareService(CareProvisionRepository careProvisionRepository) {
        this.careProvisionRepository = careProvisionRepository;
    }

    public List<Care> getAllCares(){
        return careProvisionRepository.findAllCares();
    }

    public List<Care> getAllCompatibleCares(String petTypeName){
        return careProvisionRepository.findCompatibleCares(petTypeName);
    }

    public Care getCare(String careName) {
        return null;
    }

    @Transactional(rollbackFor = {UnfeasibleCareException.class, NonCompatibleCaresException.class})
    public CareProvision save(CareProvision p) throws NonCompatibleCaresException, UnfeasibleCareException {
        List<CareProvision> careProvisions = careProvisionRepository.getCaresPerformed(p.getVisit().getId());
        for (CareProvision cp : careProvisions) {
            if (!cp.equals(p)) {
                throw new NonCompatibleCaresException();
            } else if (!p.getCare().getCompatiblePetTypes().contains(p.getVisit().getPet().getType())) {
                throw new UnfeasibleCareException();
            }
        }
        return careProvisionRepository.save(p);
    }

    public List<CareProvision> getAllCaresProvided(){
        return careProvisionRepository.findAll();
    }

    public List<CareProvision> getCaresProvided(Integer visitId){
        return null;

    }
    
}
