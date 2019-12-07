package org.planth_pheno_analytics.brapi.api.trials;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class TrialServiceImpl implements TrialService {
    private final TrialProjectionResources trialProjectionResources;
    private final TrialMapper trialMapper;
    private final TrialFilter trialFilter;

    public TrialServiceImpl(TrialProjectionResources trialProjectionResources, TrialMapper trialMapper,
                            TrialFilter trialFilter) {
        this.trialProjectionResources = trialProjectionResources;
        this.trialMapper = trialMapper;
        this.trialFilter = trialFilter;
    }

    @Override
    public List<Trial> getFilteredTrials(TrialCriteria trialCriteria) {
        Stream<Trial> trialStream = trialProjectionResources.getTrials().stream()
                .map(trialMapper::mapToTrial);

        String commonCropName = trialCriteria.getCommonCropName();
        if (isParameterPresent(commonCropName)) {
            trialStream = trialFilter.filterByCommonCropName(trialStream, commonCropName);
        }
        String programDbId = trialCriteria.getProgramDbId();
        if (isParameterPresent(programDbId)) {
            trialStream = trialFilter.filterByProgramDbId(trialStream, programDbId);
        }
        String locationDbId = trialCriteria.getLocationDbId();
        if (isParameterPresent(locationDbId)) {
            trialStream = trialFilter.filterByLocationDbId(trialStream, locationDbId);
        }
        String active = trialCriteria.getActive();
        if (isParameterPresent(active)) {
            trialStream = trialFilter.filterByActive(trialStream, active);
        }

        return trialStream.collect(Collectors.toList());
    }
}
