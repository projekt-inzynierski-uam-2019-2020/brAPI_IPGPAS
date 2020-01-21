package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;
import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmMapper;
import org.planth_pheno_analytics.data.file.FileTrial;
import org.planth_pheno_analytics.data.file.DatabaseFile;
import org.planth_pheno_analytics.data.file.DatabaseFileMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class StudyServiceImpl implements StudyService {
    private final StudyProjectionResources studyProjectionResources;
    private final StudyMapper studyMapper;
    private final StudyFilter studyFilter;
    private final StudySorter studySorter;
    private final DatabaseFileMapper databaseFileMapper;
    private final GermplasmMapper germplasmMapper;

    public StudyServiceImpl(StudyProjectionResources studyProjectionResources, GermplasmMapper germplasmMapper, StudyMapper studyMapper,
                            StudyFilter studyFilter, StudySorter studySorter, DatabaseFileMapper databaseFileMapper) {
        this.studyProjectionResources = studyProjectionResources;
        this.germplasmMapper = germplasmMapper;
        this.studyMapper = studyMapper;
        this.studyFilter = studyFilter;
        this.studySorter = studySorter;
        this.databaseFileMapper = databaseFileMapper;
    }

    @Override
    public List<Study> getFilteredAndSortedStudies(StudyCriteria studyCriteria, SortCriteria sortCriteria) {
        Stream<Study> studyStream = studyProjectionResources.getStudies().stream()
                .map(studyMapper::mapToStudy);

        String commonCropName = studyCriteria.getCommonCropName();
        if (isParameterPresent(commonCropName)) {
            studyStream = studyFilter.filterByCommonCropName(studyStream, commonCropName);
        }
        String studyTypeDbId = studyCriteria.getStudyTypeDbId();
        if (isParameterPresent(studyTypeDbId)) {
            studyStream = studyFilter.filterByStudyTypeDbId(studyStream, studyTypeDbId);
        }
        String programDbId = studyCriteria.getProgramDbId();
        if (isParameterPresent(programDbId)) {
            studyStream = studyFilter.filterByProgramDbId(studyStream, programDbId);
        }
        String locationDbId = studyCriteria.getLocationDbId();
        if (isParameterPresent(locationDbId)) {
            studyStream = studyFilter.filterByLocationDbId(studyStream, locationDbId);
        }
        String seasonDbId = studyCriteria.getSeasonDbId();
        if (isParameterPresent(seasonDbId)) {
            studyStream = studyFilter.filterBySeasonDbId(studyStream, seasonDbId);
        }
        String trialDbId = studyCriteria.getTrialDbId();
        if (isParameterPresent(trialDbId)) {
            studyStream = studyFilter.filterByTrialDbId(studyStream, trialDbId);
        }
        String studyDbId = studyCriteria.getStudyDbId();
        if (isParameterPresent(studyDbId)) {
            studyStream = studyFilter.filterByStudyDbId(studyStream, studyDbId);
        }
        String active = studyCriteria.getActive();
        if (isParameterPresent(active)) {
            studyStream = studyFilter.filterByActive(studyStream, active);
        }

        List<Study> studies = studyStream.collect(Collectors.toList());
        String sortBy = sortCriteria.getSortBy();
        if (isParameterPresent(sortBy)) {
            studies = studySorter.sortBy(studies, sortBy);
        }
        String sortOrder = sortCriteria.getSortOrder();
        if (isParameterPresent(sortOrder)) {
            studies = studySorter.sortOrder(studies, sortOrder);
        }

        return studies;
    }

    @Override
    public Map<String, List<?>> getStudiesTable(String studyDbId, String format) {
        Map<String, List<?>> result = new HashMap<>();

        Integer studyId = Integer.valueOf(studyDbId);

        Study study;
        Optional<StudyProjection> studyProjection = studyProjectionResources.getStudyByStudyDbId(studyId);
        if (studyProjection.isPresent()){
            study = studyMapper.mapToStudy(studyProjection.get());
        }
        else {
            return result;
        }

        // get file db
        DatabaseFile databaseFile = databaseFileMapper.mapToDbFile("data.json");
        // find study trial
        FileTrial trial = getStudyFileTrial(study, databaseFile).orElse(new FileTrial());

        // set headers
        List<String> headers = Arrays.asList(
                "year",
                "programDbId",
                "programName",
                "programDescription",
                "studyDbId",
                "studyName",
                "studyDescription",
                "studyDesign",
                "plotWidth",
                "plotLength",
                "fieldSize",
                "fieldTrialIsPlannedToBeGenotyped",
                "fieldTrialIsPlannedToCross",
                "plantingDate",
                "harvestDate",
                "locationDbId",
                "locationName",
                "germplasmDbId",
                "germplasmName"
        );
        result.put("headers", headers);

        // set varableIds
        List<String> observationVariableIds = new ArrayList<>(trial.getVariables().keySet());
        result.put("observationVariableIds", observationVariableIds);

        // set varableNames
        List<String> observationVariableNames = new ArrayList<>(trial.getVariables().values());
        result.put("observationVariableNames", observationVariableNames);

        // set data
        List<List<String>> data = getData(study, trial, observationVariableIds);
        result.put("data", data);
        return result;
    }

    private Optional<FileTrial> getStudyFileTrial(Study study, DatabaseFile databaseFile) {
        for (FileTrial fileTrial : databaseFile.getFileTrials()) {
            if (fileTrial.getInvestigationTitle().equals(study.getTrialName())) {
                return Optional.of(fileTrial);
            }
        }
        return Optional.empty();
    }

    private List<List<String>> getData(Study study, FileTrial trial, List<String> observationVariablesIds) {
        List<List<String>> data = new ArrayList<>();
        List<Germplasm> germplasms = getPagedStudiesGermplasmsByStudyDbId(study.getStudyDbId(), Pageable.unpaged()).getContent();
        for (Germplasm germplasm : germplasms) {
            for (String key : trial.getFinalData().keySet()) {
                if (key.equals(germplasm.getGermplasmName())) {
                    for (Map<String, String> germplasmMap : trial.getFinalData().get(key)) {
                        List<String> tableRow = new ArrayList<>();
                        if (study.getSeasons().size() > 0) {
                            tableRow.add(study.getSeasons().get(0).getYear());
                        } else {
                            tableRow.add("");
                        }
                        tableRow.add(study.getProgramDbId() == null ? "" : study.getProgramDbId());
                        tableRow.add(study.getProgramName() == null ? "" : study.getProgramDbId());
                        tableRow.add("");
                        tableRow.add(study.getStudyDbId());
                        tableRow.add(study.getStudyName());
                        StudyAdditionalInfo studyAdditionalInfo = (StudyAdditionalInfo) study.getAdditionalInfo();
                        if (studyAdditionalInfo.getDescription() != null){
                            tableRow.add(studyAdditionalInfo.getDescription());
                        }
                        else{
                            tableRow.add("");
                        }
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add("");
                        tableRow.add(study.getLocationDbId());
                        tableRow.add(study.getLocationName());
                        tableRow.add(germplasm.getGermplasmDbId());
                        tableRow.add(germplasm.getGermplasmName());
                        for (String observationVariableId: observationVariablesIds){
                            tableRow.add(germplasmMap.getOrDefault(observationVariableId, ""));
                        }
                        data.add(tableRow);
                    }
                }
            }
        }
        return data;
    }

    @Override
    public Page<Germplasm> getPagedStudiesGermplasmsByStudyDbId(String studyDbId, Pageable pageable) {
        Integer parsedStudyDbId = Integer.parseInt(studyDbId);
        return studyProjectionResources.getStudiesGermplasmsPageByStudyDbId(parsedStudyDbId, pageable)
                .map(germplasmMapper::mapToGermplasm);
    }

    @Override
    public List<Study> getStudiesWithTrialDbId(Integer trialDbId) {
        return studyProjectionResources.getStudiesWithTrialDbId(trialDbId).stream()
                .map(studyMapper::mapToStudy)
                .collect(Collectors.toList());
    }
}
