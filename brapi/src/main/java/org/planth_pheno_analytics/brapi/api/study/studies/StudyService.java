package org.planth_pheno_analytics.brapi.api.study.studies;

import org.planth_pheno_analytics.brapi.api.germplasm.Germplasm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyService {
    Page<Study> getPagedStudies(Pageable pageable);
    Page<Germplasm> getPagedStudiesGermplasmsByStudyId(String studyDbId, Pageable pageable);
    List<Study> getStudiesWithTrialDbId(Integer trialDbId);
}
