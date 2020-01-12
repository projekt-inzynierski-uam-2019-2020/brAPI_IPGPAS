package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GermplasmService {
    List<Germplasm> getFilteredGermplasms(GermplasmCriteria germplasmCriteria);
}
