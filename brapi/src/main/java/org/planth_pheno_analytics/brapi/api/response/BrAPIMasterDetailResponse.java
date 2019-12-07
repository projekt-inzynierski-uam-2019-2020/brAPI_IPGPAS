package org.planth_pheno_analytics.brapi.api.response;

import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Metadata;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BrAPIMasterDetailResponse extends BrAPIResponse {
    private Map<String, List<?>> result;

    public BrAPIMasterDetailResponse(Map<String, List<?>> result) {
        super(new Metadata.Builder()
                .withPagination(Pagination.empty())
                .build());
        this.result = result;
    }

    public Map<String, List<?>> getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrAPIMasterDetailResponse that = (BrAPIMasterDetailResponse) o;
        return Objects.equals(metadata, that.metadata) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {

        return Objects.hash(metadata, result);
    }
}
