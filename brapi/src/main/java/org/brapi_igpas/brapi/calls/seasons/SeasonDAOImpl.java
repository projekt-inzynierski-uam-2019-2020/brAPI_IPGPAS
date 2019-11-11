package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeasonDAOImpl implements SeasonDAO {
    private final RepositoryFacade repositoryFacade;
    private final SeasonMapper seasonMapper;

    @Autowired
    public SeasonDAOImpl(RepositoryFacade repositoryFacade, SeasonMapper seasonMapper) {
        this.repositoryFacade = repositoryFacade;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public List<Season> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Study start");
        return seasonMapper.mapValuesEntitiesToSeasons(valuesEntities);
    }
}
