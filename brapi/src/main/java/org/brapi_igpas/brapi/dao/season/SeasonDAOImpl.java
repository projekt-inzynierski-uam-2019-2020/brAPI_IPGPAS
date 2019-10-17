package org.brapi_igpas.brapi.dao.season;

import org.brapi_igpas.igpas.service.RepositoryFacade;
import org.brapi_igpas.igpas.service.mapper.ValueMapper;
import org.brapi_igpas.brapi.domain.calls.season.Season;
import org.brapi_igpas.igpas.entity.ValueEntityList;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeasonDAOImpl implements SeasonDAO {
    private final ValueMapper valueMapper;
    private final RepositoryFacade repositoryFacade;

    @Autowired
    public SeasonDAOImpl(ValueMapper valueMapper, RepositoryFacade repositoryFacade) {
        this.valueMapper = valueMapper;
        this.repositoryFacade = repositoryFacade;
    }

    public List<Season> getAll() {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Study start");
        return valueMapper.mapToSeasons(valuesEntities);
    }


    public List<Season> getSeasonsWithStudyId(long studyId) {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName("Study start");

        ValueEntityList valueEntityList = new ValueEntityList(valuesEntities);
        valueEntityList.filterByStudyId(studyId);

        return valueMapper.mapToSeasons(valueEntityList.getValues());
    }
}
