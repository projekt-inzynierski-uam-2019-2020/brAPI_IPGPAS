package org.brapi_igpas.brapi.dao.commoncropnames;

import org.brapi_igpas.igpas.service.RepositoryFacade;
import org.brapi_igpas.igpas.service.mapper.ValueMapper;
import org.brapi_igpas.igpas.entity.ValueEntityList;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommoncropnamesDAOImpl implements CommoncropnamesDAO {
    private final ValueMapper valueMapper;
    private final RepositoryFacade repositoryFacade;

    @Autowired
    public CommoncropnamesDAOImpl(ValueMapper valueMapper, RepositoryFacade repositoryFacade) {
        this.valueMapper = valueMapper;
        this.repositoryFacade = repositoryFacade;
    }

    @Override
    public List<String> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Organism");
        return valueMapper.mapToCommoncropnames(valuesEntities);
    }

    @Override
    public Optional<String> getCommoncropnameWithStudyId(long studyId) {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Organism");

        ValueEntityList valueEntityList = new ValueEntityList(valuesEntities);
        Optional<ValuesEntity> valuesEntity = valueEntityList.getValuesEntityWithStudyId(studyId);

        return valuesEntity.map(ValuesEntity::getValue);
    }


}
