package org.planth_pheno_analytics.brapi.api.study.studies;

import org.springframework.beans.factory.annotation.Value;

public interface StudyProjection {

    @Value("#{target.id}")
    String getStudyDbId();
    @Value("#{target.investigation_id}")
    String getTrialDbId();
    @Value("#{target.title}")
    String getName();
    @Value("#{target.title}")
    String getStudyName();
    @Value("#{target.description}")
    String getStudyAdditionalInfoDescription();
    @Value("#{target.trialName}")
    String getTrialName();
}


