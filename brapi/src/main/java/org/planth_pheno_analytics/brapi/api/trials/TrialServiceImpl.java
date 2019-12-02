package org.planth_pheno_analytics.brapi.api.trials;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrialServiceImpl implements TrialService {
    private final TrialProjectionResources trialProjectionResources;
    private final TrialMapper trialMapper;

    public TrialServiceImpl(TrialProjectionResources trialProjectionResources, TrialMapper trialMapper) {
        this.trialProjectionResources = trialProjectionResources;
        this.trialMapper = trialMapper;
    }

    @Override
    public Page<Trial> getPagedTrials(Pageable pageable) {
        return trialProjectionResources.getPagedTrialProjections(pageable)
                .map(trialMapper::mapToTrial);
    }
}
