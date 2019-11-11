package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;

public interface StudyService {
    BrAPIDetailResponse getBrAPIDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
                                               String locationDbId, String seasonDbId, String trialDbId,
                                               String studyDbId, String active, String sortBy,
                                               String sortOrder, int page, int pageSize);

    BrAPIDetailResponse getBrAPIDetailResponse(String studyDbId, int page, int pageSize);
}
