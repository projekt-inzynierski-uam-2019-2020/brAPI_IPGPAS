package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;
import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface StudyService {
    List<Study> getFilteredAndSortedStudies(StudyCriteria studyCriteria, SortCriteria sortCriteria);
    Map<String, List<?>> getStudiesTable(String studyDbId, String format);
    Page<Germplasm> getPagedStudiesGermplasmsByStudyDbId(String studyDbId, Pageable pageable);
    List<Study> getStudiesWithTrialDbId(Integer trialDbId);
}
