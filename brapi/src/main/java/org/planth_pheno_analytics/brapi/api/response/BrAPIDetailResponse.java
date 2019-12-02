package org.planth_pheno_analytics.brapi.api.response;

import org.planth_pheno_analytics.brapi.api.BrAPIResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Metadata;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BrAPIDetailResponse extends BrAPIResponse {
    private Map<String, List<?>> result;

    public BrAPIDetailResponse(Pagination pagination, List<?> resultData) {
        super(new Metadata.Builder()
                .withPagination(pagination)
                .build());
        result = new HashMap<>();
        result.put("data", resultData);
    }

    public Map<String, List<?>> getResult() {
        return result;
    }

    public void setResult(Map<String, List<?>> result) {
        this.result = result;
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
