package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StudyService {
    List<Study> getFilteredStudies(StudyCriteria studyCriteria);
    Page<Germplasm> getPagedStudiesGermplasmsByStudyDbId(String studyDbId, Pageable pageable);
    Map<String, List<?>> getStudiesTableResult(String studyDbId, String format);
    List<Study> getStudiesWithTrialDbId(Integer trialDbId);
}
