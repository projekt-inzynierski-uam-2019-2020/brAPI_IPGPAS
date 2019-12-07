package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmMapper;
import org.planth_pheno_analytics.brapi.api.germplasm.GermplasmProjectionResources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class StudyServiceImpl implements StudyService {
    private final StudyProjectionResources studyProjectionResources;
    private final StudyMapper studyMapper;
    private final StudyFilter studyFilter;
    private final GermplasmProjectionResources germplasmProjectionResources;
    private final GermplasmMapper germplasmMapper;

    public StudyServiceImpl(StudyProjectionResources studyProjectionResources, StudyMapper studyMapper,
                            StudyFilter studyFilter, GermplasmProjectionResources germplasmProjectionResources,
                            GermplasmMapper germplasmMapper) {
        this.studyProjectionResources = studyProjectionResources;
        this.studyMapper = studyMapper;
        this.studyFilter = studyFilter;
        this.germplasmProjectionResources = germplasmProjectionResources;
        this.germplasmMapper = germplasmMapper;
    }

    @Override
    public List<Study> getFilteredStudies(StudyCriteria studyCriteria) {
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

        return studyStream.collect(Collectors.toList());
    }

    @Override
    public Page<Germplasm> getPagedStudiesGermplasmsByStudyDbId(String studyDbId, Pageable pageable) {
        Integer parsedStudyDbId = Integer.parseInt(studyDbId);
        return germplasmProjectionResources.getStudiesGermplasmsPageByStudyDbId(parsedStudyDbId, pageable)
                .map(germplasmMapper::mapToGermplasm);
    }

    @Override
    public Map<String, List<?>> getStudiesTableResult(String studyDbId, String format) {
        List<List<String>> data = new ArrayList<>();
        List<String> headerRow = new ArrayList<>();
        List<String> observationVariableDbId = new ArrayList<>();
        List<String> observationVariableNames = new ArrayList<>();

        Map<String, List<?>> result = new HashMap<>();
        result.put("data", data);
        result.put("headerRow", headerRow);
        result.put("observationVariableDbId", observationVariableDbId);
        result.put("observationVariableNames", observationVariableNames);
        return result;
    }

    @Override
    public List<Study> getStudiesWithTrialDbId(Integer trialDbId) {
        return studyProjectionResources.getStudiesWithTrialDbId(trialDbId).stream()
                .map(studyMapper::mapToStudy)
                .collect(Collectors.toList());
    }
}
