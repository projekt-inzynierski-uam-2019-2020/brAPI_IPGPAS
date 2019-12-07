package org.planth_pheno_analytics.brapi.api.trials;

import org.springframework.beans.factory.annotation.Value;

public interface TrialProjection {

    @Value("#{target.title}")
    String getTrialName();
    @Value("#{target.id}")
    String getTrialDbId();
    @Value("#{target.description}")
    String getTrialAdditionalInfoDescription();
    @Value("#{target.first_contact_name}")
    String getTrialAdditionalInfoContactName();
    @Value("#{target.submission_date}")
    String getTrialAdditionalInfoSubmissionDate();
}
