package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GermplasmService {
    Page<Germplasm> getPagedGermplasms(Pageable pageable);
}
