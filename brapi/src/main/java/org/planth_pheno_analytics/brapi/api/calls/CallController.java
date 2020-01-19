package org.planth_pheno_analytics.brapi.api.calls;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class CallController {
    private final CallService callService;
    private final ResponseCreator responseCreator;

    public CallController(CallService callService, ResponseCreator responseCreator) {
        this.callService = callService;
        this.responseCreator = responseCreator;
    }

    @GetMapping("/calls")
    public BrAPIResponse getBrAPICalls(@RequestParam(value = "dataType", required = false) final String dataType,
                                       @Valid PaginationCriteria paginationCriteria) {
        final List<Call> filteredData = callService.getFilteredCalls(dataType);
        return responseCreator.createPaginatedBrAPIResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}