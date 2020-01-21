package org.planth_pheno_analytics.brapi.api;

import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.planth_pheno_analytics.brapi.utils.ManualPaginationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseCreator {

    public <T> BrAPIDetailResponse createPaginatedBrAPIResponse(List<T> filteredData, int page, int pageSize) {
        final List<T> resultData = ManualPaginationUtils.paginateList(filteredData, page, pageSize);
        final int totalPages = ManualPaginationUtils.getTotalPages(filteredData.size(), pageSize);
        final Pagination pagination = Pagination.of(page, pageSize, filteredData.size(), totalPages);
        return new BrAPIDetailResponse(pagination, resultData);
    }
}
