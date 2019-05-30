package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.calls.crops.CommoncropnamesDao;
import org.brapi_igpas.brapi.calls.study.AdditionalInfoMapper;
import org.brapi_igpas.brapi.calls.study.AdditionalInfoMapperImpl;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonDao;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.repository.InvestigationRepository;
import org.brapi_igpas.igpas.repository.StudiesRepository;
import org.brapi_igpas.igpas.entity.Investigation;
import org.brapi_igpas.igpas.entity.DbStudy;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static org.brapi_igpas.brapi.FilterUtils.isParameterPresent;

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
                        InvestigationRepository investigationRepository, SeasonDao seasonDao, CommoncropnamesDao commoncropnamesDao) {
        this.dbValuesFacade = dbValuesFacade;
        this.studiesRepository = studiesRepository;
        this.investigationRepository = investigationRepository;
        this.commoncropnamesDao = commoncropnamesDao;
        this.seasonDao = seasonDao;
        this.additionalInfoMapper = new AdditionalInfoMapperImpl(dbValuesFacade);
    }

    public BrApiDetailPayloadResponse getAll(String commonCropName, String studyTypeDbId, String programDbId,
                                             String locationDbId, String seasonDbId, String trialDbId,
                                             String studyDbId, String active, String sortBy,
                                             String sortOrder, int page, int pageSize) {
        List<DbStudy> dbStudies = (List<DbStudy>) studiesRepository.findAll();

        List<Study> studies = getStudiesFromDbStudies(dbStudies);
        setAvailableStudiesVariables(studies);
        additionalInfoMapper.setAdditionalInfoForStudies(studies);

        if (isParameterPresent(commonCropName)) {
            studies = getStudiesWithCommonCropName(studies, commonCropName);
        }

        if (isParameterPresent(studyTypeDbId)) {
            studies = getStudiesWithStudyTypeDbId(studies, studyTypeDbId);
        }

        if (isParameterPresent(programDbId)) {
            studies = getStudiesWithProgramDbId(studies, programDbId);
        }

        if (isParameterPresent(locationDbId)) {
            studies = getStudiesWithLocationDbId(studies, locationDbId);
        }

        if (isParameterPresent(seasonDbId)) {
            studies = getStudiesWithSeasonDbId(studies, seasonDbId);
        }

        if (isParameterPresent(trialDbId)) {
            studies = getStudiesWithTrialDbId(studies, trialDbId);
        }

        if (isParameterPresent(studyDbId)) {
            studies = getStudiesWithStudyDbId(studies, studyDbId);
        }

        if (isParameterPresent(active)) {
            studies = getStudiesWithActive(studies, active);
        }

        if (isParameterPresent(sortBy)) {
            studies = getSortedStudies(studies, sortBy);
        }

        if (isParameterPresent(sortOrder)) {
            orderStudiesBySortOrder(studies, sortOrder);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(studies.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(studies.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(studies.size(), page, pageSize);
        studies = studies.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(studies, paginationInfo);
    }

    private List<Study> getStudiesFromDbStudies(List<DbStudy> dbStudies) {
        return dbStudies.stream().map(dbStudy -> {
            Study study = new Study();
            study.setActive("true");
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
            return study;
        }).collect(Collectors.toCollection(ArrayList::new));
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
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStudyType(v.getValue()));
        });
    }

    private void setStudyTypesDbNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Growth facility");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStudyTypeDbName(v.getValue()));
        });
    }

    private void setLocationNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Geographic location");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setLocationName(v.getValue()));
        });
    }

    private void setStartDatesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start");
        studies.forEach(study -> {
            long studyId = Long.parseLong(study.getStudyDbId());
            dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)
                    .ifPresent(v -> study.setStartDate(v.getValue()));
        });
    }

    private void setCommonCropNamesForStudies(List<Study> studies) {
        studies.forEach(s -> {
            long studyId = Long.parseLong(s.getStudyDbId());
            s.setCommonCropName(commoncropnamesDao.getCommonCropNameForStudyWithStudyId(studyId));
        });
    }

    private void setSeasonsForStudies(List<Study> studies) {
        studies.forEach(s -> {
            long studyId = Long.parseLong(s.getStudyDbId());
            s.setSeasons(seasonDao.getSeasonsForStudyWithStudyId(studyId));
        });
    }

    private List<Study> getStudiesWithCommonCropName(List<Study> studies, String commonCropName) {
        return studies.stream().filter(s -> s.getCommonCropName() != null && s.getCommonCropName().equals(commonCropName)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithStudyTypeDbId(List<Study> studies, String studyTypeDbId) {
        return studies.stream().filter(s -> s.getStudyTypeDbId() != null && s.getStudyTypeDbId().equals(studyTypeDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithProgramDbId(List<Study> studies, String programDbId) {
        return studies.stream().filter(s -> s.getProgramDbId() != null && s.getProgramDbId().equals(programDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithLocationDbId(List<Study> studies, String locationDbId) {
        return studies.stream().filter(s -> s.getLocationDbId() != null && s.getLocationDbId().equals(locationDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithSeasonDbId(List<Study> studies, String seasonDbId) {
        return studies.stream().filter(study -> study.getSeasons().stream().anyMatch(season -> season.getSeasonDbId().equals(seasonDbId))).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithTrialDbId(List<Study> studies, String trialDbId) {
        return studies.stream().filter(s -> s.getTrialDbId() != null && s.getTrialDbId().equals(trialDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithStudyDbId(List<Study> studies, String studyDbId) {
        return studies.stream().filter(s -> s.getStudyDbId() != null && s.getStudyDbId().equals(studyDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getStudiesWithActive(List<Study> studies, String active) {
        return studies.stream().filter(s -> s.getActive() != null && s.getActive().equals(active)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Study> getSortedStudies(List<Study> studies, String sortBy) {
        switch (sortBy) {
            case "studyDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "trialDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getTrialDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "locationDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getLocationDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "programDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getProgramDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyTypeDbId":
                studies.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyTypeDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyName":
                studies.sort(nullsFirst(comparing(
                        Study::getStudyName, nullsFirst(naturalOrder())
                )));
                break;
            case "locationName":
                studies.sort(nullsFirst(comparing(
                        Study::getLocationName, nullsFirst(naturalOrder())
                )));
                break;
            case "programName":
                studies.sort(nullsFirst(comparing(
                        Study::getProgramName, nullsFirst(naturalOrder())
                )));
                break;
        }
        return studies;
    }

    private void orderStudiesBySortOrder(List<Study> studies, String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(studies);
        }
    }
}
