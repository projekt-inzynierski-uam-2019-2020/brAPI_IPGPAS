package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class TrialController {
    private final TrialService trialService;

    public TrialController(TrialService trialService) {
        this.trialService = trialService;
    }

    @GetMapping("/trials")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPITrials(@Valid TrialFilterCriteria filterCriteria, Pageable pageable) {
        final Page<Trial> resultPage = trialService.getPagedTrials(pageable);

        final Pagination pagination = Pagination.of(resultPage);
        final List<Trial> resultData = resultPage.getContent();

        return new BrAPIDetailResponse(pagination, resultData);
    }
}
