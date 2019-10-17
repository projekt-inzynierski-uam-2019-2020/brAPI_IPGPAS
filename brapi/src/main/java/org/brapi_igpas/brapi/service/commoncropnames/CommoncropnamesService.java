package org.brapi_igpas.brapi.service.commoncropnames;

import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;

public interface CommoncropnamesService {
    BrApiDetailResponse getBrApiDetailResponse(int page, int pageSize);
}
