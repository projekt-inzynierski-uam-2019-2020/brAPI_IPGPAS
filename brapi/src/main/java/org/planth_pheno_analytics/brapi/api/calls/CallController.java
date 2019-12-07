package org.planth_pheno_analytics.brapi.api.calls;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@BrAPIController
public class CallController {
    private final CallService callService;

    public CallController(CallService callService) {
        this.callService = callService;
    }

    @GetMapping("/calls")
    public BrAPIResponse getBrAPICalls(@RequestParam(value = "dataType", required = false) final String dataType,
                                       @Valid PaginationCriteria paginationCriteria) {
        final List<Call> filteredData = callService.getFilteredCalls(dataType);
        return new BrAPIDetailResponse(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
    }
}