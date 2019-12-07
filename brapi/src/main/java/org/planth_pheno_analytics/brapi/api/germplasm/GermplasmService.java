package org.planth_pheno_analytics.brapi.api.germplasm;

import java.util.List;

public interface GermplasmService {
    List<Germplasm> getFilteredGermplasms(GermplasmCriteria germplasmCriteria);
}
