package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.springframework.beans.factory.annotation.Value;

public interface SeasonProjection {

    @Value("#{target.value}")
    String getSeason();
    @Value("#{target.id}")
    String getSeasonDbId();
    @Value("#{target.value}")
    String getYear();
}
