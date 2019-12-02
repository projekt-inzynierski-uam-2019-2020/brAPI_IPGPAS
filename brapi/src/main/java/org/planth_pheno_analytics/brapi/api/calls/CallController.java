package org.planth_pheno_analytics.brapi.api.calls;

import org.planth_pheno_analytics.brapi.annotation.BrAPIController;
import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BrAPIController
public class CallController {
    private final CallService callService;
    private final ManualPaginationUtils manualPaginationUtils;

    public CallController(CallService callService, ManualPaginationUtils manualPaginationUtils) {
        this.callService = callService;
        this.manualPaginationUtils = manualPaginationUtils;
    }

    @GetMapping("/calls")
    public BrAPIResponse getBrAPICalls(@RequestParam(value = "dataType", required = false) final String dataType, Pageable pageable) {
        final List<Call> filteredData = callService.getFilteredCalls(dataType);
        Pagination pagination = manualPaginationUtils.getPagination(filteredData.size(), pageable.getPageNumber(), pageable.getPageSize());
        List<Call> resultData = manualPaginationUtils.paginateList(filteredData, pageable.getPageNumber(), pageable.getPageSize());
        return new BrAPIDetailResponse(pagination, resultData);
    }
}