package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.calls.crops.CommoncropnamesDao;
import org.brapi_igpas.brapi.calls.study.AdditionalInfoMapper;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonDao;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.repository.InvestigationRepository;
import org.brapi_igpas.igpas.repository.StudiesRepository;
import org.brapi_igpas.igpas.entity.Investigation;
import org.brapi_igpas.igpas.entity.DbStudy;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudyDaoImpl implements StudyDao {
    private final DbValuesFacade dbValuesFacade;
    private final SeasonDao seasonDao;
    private final CommoncropnamesDao commoncropnamesDao;
    private final StudiesRepository studiesRepository;
    private final AdditionalInfoMapper additionalInfoMapper;
    private final InvestigationRepository investigationRepository;

    @Autowired
    public StudyDaoImpl(DbValuesFacade dbValuesFacade, StudiesRepository studiesRepository,
                        InvestigationRepository investigationRepository, SeasonDao seasonDao,
                        CommoncropnamesDao commoncropnamesDao, AdditionalInfoMapper additionalInfoMapper) {
        this.dbValuesFacade = dbValuesFacade;
        this.studiesRepository = studiesRepository;
        this.investigationRepository = investigationRepository;
        this.commoncropnamesDao = commoncropnamesDao;
        this.seasonDao = seasonDao;
        this.additionalInfoMapper = additionalInfoMapper;
    }

    public List<Study> getAll() {
        List<DbStudy> dbStudies = (List<DbStudy>) studiesRepository.findAll();

        List<Study> studies = getStudiesFromDbStudies(dbStudies);
        setAvailableStudiesVariables(studies);
        additionalInfoMapper.setAdditionalInfoForStudies(studies);

        return studies;
    }

    private List<Study> getStudiesFromDbStudies(List<DbStudy> dbStudies) {
        List<Study> studies = new ArrayList<>();
        for (DbStudy dbStudy : dbStudies) {
            Study study = new Study();
            study.setStudyDbId(String.valueOf(dbStudy.getId()));
            study.setTrialDbId(String.valueOf(dbStudy.getInvestigationId()));
            if (dbStudy.getTitle() != null) {
                study.setName(dbStudy.getTitle());
                study.setStudyName(dbStudy.getTitle());
            }
            study.setAdditionalInfo(new StudyAdditionalInfo());
            Object additionalInfo = study.getAdditionalInfo();
            if (dbStudy.getDescription() != null) {
                ((StudyAdditionalInfo) additionalInfo).setDescription(dbStudy.getDescription());
            }
            studies.add(study);
        }
        return studies;
    }

    private void setAvailableStudiesVariables(List<Study> studies) {
        setTrialNamesForStudies(studies);
        setStudyTypesForStudies(studies);
        setStudyTypesDbNamesForStudies(studies);
        setLocationNamesForStudies(studies);
        setStartDatesForStudies(studies);
        setCommonCropNamesForStudies(studies);
        setSeasonsForStudies(studies);
    }

    private void setTrialNamesForStudies(List<Study> studies) {
        studies.forEach(study -> {
            long investigationId = Long.parseLong(study.getTrialDbId());
            Optional<Investigation> investigation = investigationRepository.getInvestigationById(investigationId);
            investigation.ifPresent(i -> study.setTrialName(i.getTitle()));
        });
    }

    private void setStudyTypesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Growth facility");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStudyType(v.getValue()));
        });
    }

    private void setStudyTypesDbNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Growth facility");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStudyTypeDbName(v.getValue()));
        });
    }

    private void setLocationNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Geographic location");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setLocationName(v.getValue()));
        });
    }

    private void setStartDatesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStartDate(v.getValue()));
        });
    }

    private void setCommonCropNamesForStudies(List<Study> studies) {
        studies.forEach(s -> {
            long studyId = Long.parseLong(s.getStudyDbId());
            s.setCommonCropName(commoncropnamesDao.getCommonCropNameWithStudyId(studyId));
        });
    }

    private void setSeasonsForStudies(List<Study> studies) {
        studies.forEach(s -> {
            long studyId = Long.parseLong(s.getStudyDbId());
            s.setSeasons(seasonDao.getSeasonsForStudyWithStudyId(studyId));
        });
    }
}
