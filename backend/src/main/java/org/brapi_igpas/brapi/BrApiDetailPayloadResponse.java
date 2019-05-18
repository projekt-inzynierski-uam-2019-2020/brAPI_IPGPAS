package org.brapi_igpas.brapi;

import org.brapi_igpas.brapi.metadata.Metadata;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.result.Result;

import java.util.List;

public class BrApiDetailPayloadResponse {
    private Metadata metadata;
    private Result result;

    public BrApiDetailPayloadResponse() {
        metadata = new Metadata();
        result = new Result();
    }

    public BrApiDetailPayloadResponse(List<?> elements, Pagination pagination) {
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
}
