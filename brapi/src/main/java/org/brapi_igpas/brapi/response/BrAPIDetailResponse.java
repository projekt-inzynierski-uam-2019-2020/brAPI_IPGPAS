package org.brapi_igpas.brapi.response;

import org.brapi_igpas.brapi.response.metadata.Metadata;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.response.result.Result;

import java.util.List;
import java.util.Objects;

public class BrAPIDetailResponse {
    private Metadata metadata;
    private Result result;

    public BrAPIDetailResponse() {
        metadata = new Metadata();
        result = new Result();
    }

    public BrAPIDetailResponse(Pagination pagination, List<?> elements) {
        this();
        metadata.setPagination(pagination);
        result.setData(elements);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Result getResult() {
        return result;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrAPIDetailResponse that = (BrAPIDetailResponse) o;
        return Objects.equals(metadata, that.metadata) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, result);
    }
}
