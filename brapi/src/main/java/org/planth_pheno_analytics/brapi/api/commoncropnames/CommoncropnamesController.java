package org.planth_pheno_analytics.brapi.api.commoncropnames;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class CommoncropnamesController {
    private final CommoncropnamesProjectionResources commoncropnamesProjectionResources;
    private final ResponseCreator responseCreator;

    public CommoncropnamesController(CommoncropnamesProjectionResources commoncropnamesProjectionResources, ResponseCreator responseCreator) {
        this.commoncropnamesProjectionResources = commoncropnamesProjectionResources;
        this.responseCreator = responseCreator;
    }

    @GetMapping("/commoncropnames")
    @ResponseStatus(HttpStatus.OK)
    public BrAPIResponse getBrAPICommoncropnames(@Valid PaginationCriteria paginationCriteria) {
        final List<String> result = commoncropnamesProjectionResources.getCommoncropnames();
        return responseCreator.createPaginatedBrAPIResponse(result, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}