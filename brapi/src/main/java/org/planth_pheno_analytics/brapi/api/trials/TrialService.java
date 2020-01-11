package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;

import java.util.List;

public interface TrialService {
    List<Trial> getFilteredAndSortedTrials(TrialCriteria trialCriteria, SortCriteria sortCriteria);
}
