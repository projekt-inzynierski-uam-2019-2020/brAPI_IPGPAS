package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommoncropnamesDAOImpl implements CommoncropnamesDAO {
    private final RepositoryFacade repositoryFacade;
    private final CommoncropnamesMapper commoncropnamesMapper;

    @Autowired
    public CommoncropnamesDAOImpl(RepositoryFacade repositoryFacade, CommoncropnamesMapper commoncropnamesMapper) {
        this.repositoryFacade = repositoryFacade;
        this.commoncropnamesMapper = commoncropnamesMapper;
    }

    @Override
    public List<String> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Organism");
        return commoncropnamesMapper.mapValuesEntitiesToCommoncropnames(valuesEntities);
    }
}
