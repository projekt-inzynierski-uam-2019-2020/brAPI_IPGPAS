package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GermplasmDAOImpl implements GermplasmDAO {
    private final RepositoryFacade repositoryFacade;
    private final GermplasmMapper germplasmMapper;

    @Autowired
    public GermplasmDAOImpl(RepositoryFacade repositoryFacade, GermplasmMapper germplasmMapper) {
        this.repositoryFacade = repositoryFacade;
        this.germplasmMapper = germplasmMapper;
    }

    public List<Germplasm> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Infraspecific name");
        return germplasmMapper.mapValuesEntitiesToGermplasms(valuesEntities);
    }
}
