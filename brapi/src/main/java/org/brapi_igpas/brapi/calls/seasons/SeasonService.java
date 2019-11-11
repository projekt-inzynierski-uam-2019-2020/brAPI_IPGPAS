package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface SeasonService {
    BrAPIDetailResponse getBrAPIDetailResponse(String seasonDbId, String season, String year, int page, int pageSize);
}
