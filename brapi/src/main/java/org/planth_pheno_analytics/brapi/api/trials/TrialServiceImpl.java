package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;
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
    private final TrialSorter trialSorter;

    public TrialServiceImpl(TrialProjectionResources trialProjectionResources, TrialMapper trialMapper,
                            TrialFilter trialFilter, TrialSorter trialSorter) {
        this.trialProjectionResources = trialProjectionResources;
        this.trialMapper = trialMapper;
        this.trialFilter = trialFilter;
        this.trialSorter = trialSorter;
    }

    @Override
    public List<Trial> getFilteredAndSortedTrials(TrialCriteria trialCriteria, SortCriteria sortCriteria) {
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

        List<Trial> trials = trialStream.collect(Collectors.toList());
        String sortBy = sortCriteria.getSortBy();
        if (isParameterPresent(sortBy)) {
            trials = trialSorter.sortBy(trials, sortBy);
        }
        String sortOrder = sortCriteria.getSortOrder();
        if (isParameterPresent(active)) {
            trials = trialSorter.sortOrder(trials, sortOrder);
        }

        return trials;
    }
}
