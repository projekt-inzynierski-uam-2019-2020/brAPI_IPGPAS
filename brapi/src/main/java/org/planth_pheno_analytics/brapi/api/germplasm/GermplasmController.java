package org.planth_pheno_analytics.brapi.api.germplasm;

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
public class GermplasmController {
    private final GermplasmService germplasmService;

    public GermplasmController(GermplasmService germplasmService) {
        this.germplasmService = germplasmService;
    }

    @GetMapping("/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIGermplasm(@Valid GermplasmFilterCriteria filterCriteria, Pageable pageable) {
        final Page<Germplasm> resultPage = germplasmService.getPagedGermplasms(pageable);

        final Pagination pagination = Pagination.of(resultPage);
        final List<Germplasm> resultData = resultPage.getContent();

        return new BrAPIDetailResponse(pagination, resultData);
    }
}
