package org.planth_pheno_analytics.brapi.api.response;

import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Metadata;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.planth_pheno_analytics.brapi.utils.ManualPaginationUtils;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BrAPIDetailResponse extends BrAPIResponse {
    private Map<String, List<?>> result = new HashMap<>();

    public BrAPIDetailResponse(List<?> resultData, int page, int pageSize) {
        super(new Metadata.Builder()
                .withPagination(Pagination.of(
                        page,
                        pageSize,
                        resultData.size(),
                        ManualPaginationUtils.getTotalPages(resultData.size(), pageSize)))
                .build());
        result.put("data", ManualPaginationUtils.paginateList(resultData, page, pageSize));
    }

    public BrAPIDetailResponse(Page<?> page) {
        super(new Metadata.Builder()
                .withPagination(Pagination.of(
                        page.getNumber(),
                        page.getSize(),
                        page.getTotalElements(),
                        page.getTotalPages()))
                .build());
        result.put("data", page.getContent());
    }

    public Map<String, List<?>> getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrAPIDetailResponse response = (BrAPIDetailResponse) o;
        return Objects.equals(metadata, response.metadata) &&
                Objects.equals(result, response.result);
    }

    @Override
    public int hashCode() {

        return Objects.hash(metadata, result);
    }
}
