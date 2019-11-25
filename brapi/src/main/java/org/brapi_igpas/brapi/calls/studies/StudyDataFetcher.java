package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.calls.seasons.Season;
import org.brapi_igpas.brapi.calls.seasons.SeasonMapper;
import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.InvestigationsEntityRepository;
import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudyDataFetcher {
    private final InvestigationsEntityRepository investigationsEntityRepository;
    private final RepositoryFacade repositoryFacade;
    private final SeasonMapper seasonMapper;

    public StudyDataFetcher(InvestigationsEntityRepository investigationsEntityRepository,
                            RepositoryFacade repositoryFacade, SeasonMapper seasonMapper) {
        this.investigationsEntityRepository = investigationsEntityRepository;
        this.repositoryFacade = repositoryFacade;
        this.seasonMapper = seasonMapper;
    }

    Optional<String> getTrialNameByStudyId(long studyId) {
        Optional<InvestigationsEntity> investigationsEntity = investigationsEntityRepository.getInvestigationById(studyId);
        return investigationsEntity.map(InvestigationsEntity::getTitle);
    }

    Optional<String> getStudyTypeByStudyId(long studyId) {
        Optional<ValuesEntity> valuesEntity = getValuesEntityWithStudyId(studyId, "Growth facility");
        return valuesEntity.map(ValuesEntity::getValue);
    }

    Optional<String> getLocationNameByStudyId(long studyId) {
        Optional<ValuesEntity> valuesEntity = getValuesEntityWithStudyId(studyId, "Geographic location");
        return valuesEntity.map(ValuesEntity::getValue);
    }

    Optional<String> getStartDateByStudyId(long studyId) {
        Optional<ValuesEntity> valuesEntity = getValuesEntityWithStudyId(studyId, "Study start");
        return valuesEntity.map(ValuesEntity::getValue);
    }

    Optional<String> getCommoncropnameByStudyId(long studyId) {
        Optional<ValuesEntity> valuesEntityWithStudyId = getValuesEntityWithStudyId(studyId, "Organism");
        return valuesEntityWithStudyId.map(ValuesEntity::getValue);
    }

    private Optional<ValuesEntity> getValuesEntityWithStudyId(long studyId, String displayedName) {
        List<ValuesEntity> valuesEntities = repositoryFacade.getValuesEntitiesByAttributeDisplayedName(displayedName);
        return valuesEntities.stream().filter(v -> v.getStudyId() == studyId).findFirst();
    }

    List<Season> getSeasonsByStudyId(long studyId) {
        List<ValuesEntity> valuesEntities = repositoryFacade.getValuesEntitiesByAttributeDisplayedName("Study start");

        valuesEntities = valuesEntities.stream().filter(valuesEntity -> valuesEntity.getStudyId() == studyId)
                .collect(Collectors.toCollection(ArrayList::new));

        return seasonMapper.mapValuesEntitiesToSeasons(valuesEntities);
    }
}
