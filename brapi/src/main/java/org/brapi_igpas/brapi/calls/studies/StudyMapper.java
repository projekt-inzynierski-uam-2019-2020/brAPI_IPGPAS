package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.calls.seasons.Season;
import org.brapi_igpas.igpas.entity.StudiesEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudyMapper {
    private final StudyDataFetcher studyDataFetcher;
    private final StudyAdditionalInfoMapper studyAdditionalInfoMapper;

    public StudyMapper(StudyDataFetcher studyDataFetcher, StudyAdditionalInfoMapper studyAdditionalInfoMapper) {
        this.studyDataFetcher = studyDataFetcher;
        this.studyAdditionalInfoMapper = studyAdditionalInfoMapper;
    }

    List<Study> mapStudiesEntitiesToStudies(List<StudiesEntity> studiesEntities) {
        List<Study> studies = new ArrayList<>();
        for (StudiesEntity studiesEntity : studiesEntities) {
            Study study = mapStudiesEntityToStudy(studiesEntity);
            studies.add(study);
        }
        studyAdditionalInfoMapper.mapAdditionalInfoForList(studies);
        return studies;
    }

    Study mapStudiesEntityToStudy(StudiesEntity studiesEntity) {
        Study study = new Study();
        study.setStudyDbId(String.valueOf(studiesEntity.getId()));
        study.setTrialDbId(String.valueOf(studiesEntity.getInvestigationId()));
        study.setDocumentationURL("http://cropnet.pl/plantphenodb/index.php?id=" + study.getTrialDbId());

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

        Optional<String> commonCropName = studyDataFetcher.getCommoncropnameByStudyId(studyId);
        commonCropName.ifPresent(study::setCommonCropName);

        List<Season> seasons = studyDataFetcher.getSeasonsByStudyId(studyId);
        study.setSeasons(seasons);

        Object additionalInfo = new StudyAdditionalInfo();
        Optional<String> description = Optional.ofNullable(studiesEntity.getDescription());
        description.ifPresent(((StudyAdditionalInfo) additionalInfo)::setDescription);
        study.setAdditionalInfo(additionalInfo);
        return study;
    }
}
