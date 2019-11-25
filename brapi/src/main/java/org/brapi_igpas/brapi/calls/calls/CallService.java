package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface CallService {
    BrAPIDetailResponse getBrAPIDetailResponse(String dataType, int page, int pageSize);
}
