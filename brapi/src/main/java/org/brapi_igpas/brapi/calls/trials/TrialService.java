package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface TrialService {
    BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String programDbId, String locationDbId,
                                               String active, String sortBy, String sortOrder, int page, int pageSize);
}
