package org.brapi_igpas.brapi.dao.germplasm;

import org.brapi_igpas.igpas.service.RepositoryFacade;
import org.brapi_igpas.igpas.service.mapper.ValueMapper;
import org.brapi_igpas.brapi.domain.calls.germplasm.Germplasm;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GermplasmDAOImpl implements GermplasmDAO {
    private final ValueMapper valueMapper;
    private final RepositoryFacade repositoryFacade;

    @Autowired
    public GermplasmDAOImpl(ValueMapper valueMapper, RepositoryFacade repositoryFacade) {
        this.valueMapper = valueMapper;
        this.repositoryFacade = repositoryFacade;
    }

    public List<Germplasm> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Infraspecific name");
        return valueMapper.mapToGermplasms(valuesEntities);
    }
}
