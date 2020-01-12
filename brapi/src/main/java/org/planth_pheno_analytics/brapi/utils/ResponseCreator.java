package org.planth_pheno_analytics.brapi.utils;

import org.planth_pheno_analytics.brapi.api.criteria.PaginationCriteria;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;

import java.util.List;

public class ResponseCreator {

    public static <T> BrAPIDetailResponse createBrAPIResponse(List<T> filteredData, PaginationCriteria paginationCriteria) {
        final List<T> resultData = ManualPaginationUtils.paginateList(filteredData, paginationCriteria.getPage(), paginationCriteria.getPageSize());
        final int totalPages = ManualPaginationUtils.getTotalPages(resultData.size(), paginationCriteria.getPageSize());
        final Pagination pagination = Pagination.of(paginationCriteria.getPage(), paginationCriteria.getPageSize(), filteredData.size(), totalPages);
        return new BrAPIDetailResponse(pagination, resultData);
    }


}
