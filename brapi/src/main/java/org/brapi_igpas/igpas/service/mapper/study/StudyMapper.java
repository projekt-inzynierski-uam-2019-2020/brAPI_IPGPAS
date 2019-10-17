package org.brapi_igpas.igpas.service.mapper.study;

import org.brapi_igpas.brapi.dao.commoncropnames.CommoncropnamesDAO;
import org.brapi_igpas.brapi.dao.season.SeasonDAO;
import org.brapi_igpas.brapi.domain.calls.season.Season;
import org.brapi_igpas.brapi.domain.calls.study.Study;
import org.brapi_igpas.brapi.domain.calls.study.StudyAdditionalInfo;
import org.brapi_igpas.igpas.entity.StudiesEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudyMapper {
    private final StudyDataFetcher studyDataFetcher;
    private final SeasonDAO seasonDAO;
    private final CommoncropnamesDAO commoncropnamesDAO;

    public StudyMapper(StudyDataFetcher studyDataFetcher, SeasonDAO seasonDAO, CommoncropnamesDAO commoncropnamesDAO) {
        this.studyDataFetcher = studyDataFetcher;
        this.seasonDAO = seasonDAO;
        this.commoncropnamesDAO = commoncropnamesDAO;
    }

    public List<Study> mapToStudies(List<StudiesEntity> studiesEntities) {
        List<Study> studies = new ArrayList<>();
        for (StudiesEntity studiesEntity : studiesEntities) {
            Study study = new Study();
            study.setStudyDbId(String.valueOf(studiesEntity.getId()));
            study.setTrialDbId(String.valueOf(studiesEntity.getInvestigationId()));
            study.setDocumentationURL("cropnet.pl/plantphenodb/?id=" + study.getTrialDbId());

            Optional<String> title = Optional.ofNullable(studiesEntity.getTitle());
            title.ifPresent(study::setName);
            title.ifPresent(study::setStudyName);

            long studyId = Long.parseLong(study.getStudyDbId());
            Optional<String> trialName = studyDataFetcher.getTrialNameByStudyId(studyId);
            trialName.ifPresent(study::setTrialName);

            Optional<String> value = studyDataFetcher.getStudyTypeByStudyId(studyId);
            value.ifPresent(study::setStudyType);
            value.ifPresent(study::setStudyTypeDbName);

            value = studyDataFetcher.getLocationNameByStudyId(studyId);
            value.ifPresent(study::setLocationName);

            value = studyDataFetcher.getStartDateByStudyId(studyId);
            value.ifPresent(study::setStartDate);

            Optional<String> commonCropName = commoncropnamesDAO.getCommoncropnameWithStudyId(studyId);
            commonCropName.ifPresent(study::setCommonCropName);

            List<Season> seasons = seasonDAO.getSeasonsWithStudyId(studyId);
            study.setSeasons(seasons);

            Object additionalInfo = new StudyAdditionalInfo();
            Optional<String> description = Optional.ofNullable(studiesEntity.getDescription());
            description.ifPresent(((StudyAdditionalInfo) additionalInfo)::setDescription);
            study.setAdditionalInfo(additionalInfo);

            studies.add(study);
        }
        return studies;
    }
}