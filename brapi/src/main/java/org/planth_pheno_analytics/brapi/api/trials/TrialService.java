package org.planth_pheno_analytics.brapi.api.trials;

import java.util.List;

public interface TrialService {
    List<Trial> getFilteredTrials(TrialCriteria trialCriteria);
}
