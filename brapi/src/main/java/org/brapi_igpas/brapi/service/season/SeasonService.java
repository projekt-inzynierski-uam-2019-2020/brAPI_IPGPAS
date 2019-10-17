package org.brapi_igpas.brapi.service.season;

import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;

public interface SeasonService {
    BrApiDetailResponse getBrApiDetailResponse(String seasonDbId, String season, String year, int page, int pageSize);
}
