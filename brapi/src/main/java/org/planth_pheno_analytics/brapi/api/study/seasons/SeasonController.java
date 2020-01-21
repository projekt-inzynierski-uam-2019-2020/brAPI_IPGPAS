package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class SeasonController {
    private final SeasonService seasonService;
    private final ResponseCreator responseCreator;

    public SeasonController(SeasonService seasonService, ResponseCreator responseCreator) {
        this.seasonService = seasonService;
        this.responseCreator = responseCreator;
    }

    @GetMapping("/seasons")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPISeasons(@Valid SeasonCriteria seasonCriteria, @Valid PaginationCriteria paginationCriteria) {
        final List<Season> filteredData = seasonService.getFilteredSeasons(seasonCriteria);
        return responseCreator.createPaginatedBrAPIResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}
