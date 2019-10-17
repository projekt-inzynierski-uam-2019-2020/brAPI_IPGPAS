package org.brapi_igpas.igpas.service.mapper.study;

import org.brapi_igpas.igpas.entity.InvestigationsEntity;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.InvestigationRepository;
import org.brapi_igpas.igpas.service.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyDataFetcher {
    private final RepositoryFacade repositoryFacade;
    private final InvestigationRepository investigationRepository;

    @Autowired
    public StudyDataFetcher(RepositoryFacade repositoryFacade, InvestigationRepository investigationRepository) {
        this.repositoryFacade = repositoryFacade;
        this.investigationRepository = investigationRepository;
    }

    Optional<String> getTrialNameByStudyId(long studyId) {
        Optional<InvestigationsEntity> investigationsEntity = investigationRepository.getInvestigationById(studyId);
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

    private Optional<ValuesEntity> getValuesEntityWithStudyId(long studyId, String displayedName) {
        List<ValuesEntity> valuesEntities = repositoryFacade.getDistinctValuesByAttributeDisplayedName(displayedName);
        return valuesEntities.stream().filter(v -> v.getStudyId() == studyId).findFirst();
    }
}
