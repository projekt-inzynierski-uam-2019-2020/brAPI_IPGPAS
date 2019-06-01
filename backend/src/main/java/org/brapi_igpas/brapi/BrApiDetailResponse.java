package org.brapi_igpas.brapi;

import org.brapi_igpas.brapi.metadata.Metadata;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.result.Result;

import java.util.List;
import java.util.Objects;

public class BrApiDetailResponse {
    private Metadata metadata;
    private Result result;

    public BrApiDetailResponse() {
        metadata = new Metadata();
        result = new Result();
    }

    public BrApiDetailResponse(List<?> elements, Pagination pagination) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrApiDetailResponse that = (BrApiDetailResponse) o;
        return Objects.equals(metadata, that.metadata) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, result);
    }
}
