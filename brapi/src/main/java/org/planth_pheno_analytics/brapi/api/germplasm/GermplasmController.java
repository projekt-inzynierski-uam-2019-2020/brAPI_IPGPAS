package org.planth_pheno_analytics.brapi.api.germplasm;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
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
    public BrAPIResponse getBrAPIGermplasm(@Valid GermplasmCriteria germplasmCriteria,
                                           @Valid PaginationCriteria paginationCriteria) {
        final List<Germplasm> filteredData = germplasmService.getFilteredGermplasms(germplasmCriteria);
        return new BrAPIDetailResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}
