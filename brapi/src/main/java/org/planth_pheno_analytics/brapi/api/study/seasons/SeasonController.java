package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class SeasonController {
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/seasons")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPISeasons(@Valid SeasonFilterCriteria filterCriteria, Pageable pageable) {
        final Page<Season> resultPage = seasonService.getPagedSeasons( pageable);

        final Pagination pagination = Pagination.of(resultPage);
        final List<Season> resultData = resultPage.getContent();

        return new BrAPIDetailResponse(pagination, resultData);
    }
}
