package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.calls.study.seasons.Season;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonDao;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.repository.InvestigationRepository;
import org.brapi_igpas.igpas.repository.StudiesRepository;
import org.brapi_igpas.igpas.entity.Investigation;
import org.brapi_igpas.igpas.entity.Studies;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.FilterUtils.isParameterPresent;

@Repository
public class StudyDao {
    private final DbValuesFacade dbValuesFacade;
    private final StudiesRepository studiesRepository;
    private final InvestigationRepository investigationRepository;
    private final SeasonDao seasonDao;

    @Autowired
    public StudyDao(DbValuesFacade dbValuesFacade, StudiesRepository studiesRepository, InvestigationRepository investigationRepository, SeasonDao seasonDao) {
        this.dbValuesFacade = dbValuesFacade;
        this.studiesRepository = studiesRepository;
        this.investigationRepository = investigationRepository;
        this.seasonDao = seasonDao;
    }

    public BrApiDetailPayloadResponse getAll(String commonCropName, String studyTypeDbId, String programDbId,
                                             String locationDbId, String seasonDbId, String trialDbId,
                                             String studyDbId, String active, String sortBy,
                                             String sortOrder, int page, int pageSize) {
        List<Studies> dbStudies = (List<Studies>) studiesRepository.findAll();

        List<Study> studies = initializeStudiesWithDbStudies(dbStudies);
        setTrialNamesForStudies(studies);
        setStudyTypesIdsForStudies(studies);
        setStudyTypesForStudies(studies);
        setStudyTypesDbNamesForStudies(studies);
        setLocationDbIdsForStudies(studies);
        setLocationNamesForStudies(studies);
        setCommonCropNamesForStudies(studies);
        setStartDatesForStudies(studies);
        setSeasonsForStudies(studies);


        // 7. BrAPI::startDate -> PPDB::attributes::displayedName ==  Study start <-- ISO a mamy tylko rok
        //BrAPI::commonCropName -> PPDB::attributes::displayedName ==  Organism
        //BrAPI::locationName -> PPDB::attributes::displayedName ==  Geographic location
        //BrAPI::studyType, studyTypeName -> PPDB::attributes::displayedName ==  Growth facility
        //BrAPI::studyName, name -> PPDB::studies::title
        //BrAPI::studyDbId -> PPDB::studies::id
        //BrAPI::trialName -> PPDB::investigations::title
        //BrAPI::trailDbId -> PPDB::studies::investigation_id


        // 10. BrAPI::additional info? -> PPDB::studies::description
        // 11. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Volume
        // 12. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Type of fertiliser
        // 13. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Sowing density
        // 14. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Plot size
        // 15. BrAPI::additional info? -> PPDB::attributes::displayedName ==  pH
        // 16. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Number of plants per container
        // 17. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Night temperature
        // 18. BrAPI::additional info? -> PPDB::attributes::displayedName ==  N before fertilisation
        // 19. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Length of light period
        // 20. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Irrigation type
        // 21. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Frequency
        // 22. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Factor
        // 23. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Experimental unit
        // 24. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Day temperature
        // 25. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Daily photon flux
        // 26. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Container volume
        // 27. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Container type
        // 28. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Container dimension
        // 29. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Characteristics
        // 30. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Assay Type
        // 31. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Amount of fertiliser
        // 32. BrAPI::additional info? -> PPDB::attributes::displayedName ==  Air humidity

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

        ///////////////////////////////////////
        if (isParameterPresent(sortBy)) {
        }

        if (isParameterPresent(sortOrder)) {
        }
        //////////////////////////////////////


        Pagination paginationInfo = PaginationUtils.getPaginationInfo(studies.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(studies.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(studies.size(), page, pageSize);
        studies = studies.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(studies, paginationInfo);
    }

    private List<Study> initializeStudiesWithDbStudies(List<Studies> dbStudies) {
        return dbStudies.stream().map(temp -> {
            Study study = new Study();
            study.setActive("true");
            study.setStudyDbId(String.valueOf(temp.getId()));
            study.setName(temp.getTitle());
            study.setStudyName(temp.getTitle());
            study.setTrialDbId(String.valueOf(temp.getInvestigationId()));
            return study;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private void setTrialNamesForStudies(List<Study> studies) {
        for (Study study : studies) {
            Optional<Investigation> investigation = investigationRepository.getInvestigationById(Long.parseLong(study.getTrialDbId()));
            investigation.ifPresent(i -> study.setTrialName(i.getTitle()));
        }
    }

    private void setStudyTypesIdsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Growth facility");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setStudyTypeDbId(String.valueOf(value.getId())));
        }
    }

    private void setStudyTypesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Growth facility");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setStudyType(value.getValue()));
        }
    }

    private void setStudyTypesDbNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Growth facility");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setStudyTypeDbName(value.getValue()));
        }
    }

    private void setLocationDbIdsForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Geographic location");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setLocationDbId(String.valueOf(value.getId())));
        }
    }

    private void setLocationNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Geographic location");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setLocationName(value.getValue()));
        }
    }

    private void setCommonCropNamesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Organism");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setCommonCropName(value.getValue()));
        }
    }

    private void setStartDatesForStudies(List<Study> studies) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Study start");
        for (Study study : studies) {
            values.stream().filter(v -> v.getStudyId() == Long.parseLong(study.getStudyDbId())).findAny().ifPresent(value -> study.setStartDate(value.getValue()));
        }
    }

    private void setSeasonsForStudies(List<Study> studies){
        studies.forEach(s -> s.setSeasons(seasonDao.getSeasonsForStudyWithStudyId(Long.parseLong(s.getStudyDbId()))));
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
    //TODO refactor
    private List<Study> getStudiesWithSeasonDbId(List<Study> studies, String seasonDbId) {
        List<Study> result = new ArrayList<>();
        for (Study study : studies){
            for (Season season : study.getSeasons()){
                if(season.getSeasonDbId().equals(seasonDbId)){
                    result.add(study);
                    break;
                }
            }
        }
        return result;
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
}
