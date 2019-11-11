package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface CommoncropnamesService {
    BrAPIDetailResponse getBrAPIDetailResponse(int page, int pageSize);
}
