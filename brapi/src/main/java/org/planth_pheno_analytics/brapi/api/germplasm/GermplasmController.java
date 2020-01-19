package org.planth_pheno_analytics.brapi.api.germplasm;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class GermplasmController {
    private final GermplasmService germplasmService;
    private final ResponseCreator responseCreator;

    public GermplasmController(GermplasmService germplasmService, ResponseCreator responseCreator) {
        this.germplasmService = germplasmService;
        this.responseCreator = responseCreator;
    }

    @GetMapping("/germplasm")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPIGermplasm(@Valid GermplasmCriteria germplasmCriteria, @Valid PaginationCriteria paginationCriteria) {
        final List<Germplasm> filteredData = germplasmService.getFilteredGermplasms(germplasmCriteria);
        return responseCreator.createPaginatedBrAPIResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}
