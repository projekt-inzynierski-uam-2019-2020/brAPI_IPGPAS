package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.brapi_igpas.igpas.repository.InvestigationsEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrialDAOImpl implements TrialDAO {
    private final InvestigationsEntityRepository investigationsEntityRepository;
    private final TrialMapper trialMapper;


    public TrialDAOImpl(TrialMapper trialMapper, InvestigationsEntityRepository investigationsEntityRepository) {
        this.investigationsEntityRepository = investigationsEntityRepository;
        this.trialMapper = trialMapper;
    }

    @Override
    public List<Trial> getAll() {
        List<InvestigationsEntity> investigationsEntities = (List<InvestigationsEntity>) investigationsEntityRepository.findAll();
        return trialMapper.mapInvestigationsEntitiesToTrials(investigationsEntities);
    }
}
