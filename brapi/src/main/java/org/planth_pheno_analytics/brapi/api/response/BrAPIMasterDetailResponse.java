package org.planth_pheno_analytics.brapi.api.response;

import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Metadata;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BrAPIMasterDetailResponse extends BrAPIResponse {
    private Map<String, ?> result;

    public BrAPIMasterDetailResponse() {
        super(new Metadata.Builder()
                .withPagination(Pagination.empty())
                .build());
        result = new HashMap<>();
    }

    public Map<String, ?> getResult() {
        return result;
    }

    public void setResult(Map<String, ?> result) {
        this.result = result;
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
