package org.planth_pheno_analytics.brapi.api.trials;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrialService {
    Page<Trial> getPagedTrials(Pageable pageable);
}
