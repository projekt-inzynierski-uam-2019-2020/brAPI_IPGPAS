package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.criteria.SortCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class TrialController {
    private final TrialService trialService;
    private final ResponseCreator responseCreator;

    public TrialController(TrialService trialService, ResponseCreator responseCreator) {
        this.trialService = trialService;
        this.responseCreator = responseCreator;
    }

    @GetMapping("/trials")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPITrials(@Valid TrialCriteria trialCriteria, @Valid PaginationCriteria paginationCriteria,
                                        @Valid SortCriteria sortCriteria) {
        final List<Trial> filteredData = trialService.getFilteredAndSortedTrials(trialCriteria, sortCriteria);
        return responseCreator.createPaginatedBrAPIResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}
