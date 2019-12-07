package org.planth_pheno_analytics.brapi.api.germplasm;


import org.springframework.beans.factory.annotation.Value;

public interface GermplasmProjection {

    @Value("#{target.value}")
    String getGermplasmDbId();
    @Value("#{target.value}")
    String getAccessionNumber();
    @Value("#{target.value}")
    String getDefaultDisplayName();
    @Value("#{target.value}")
    String getGermplasmName();
}
