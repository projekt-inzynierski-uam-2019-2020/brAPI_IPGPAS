package org.brapi_igpas.brapi.service.study;

import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;

public interface StudyService {
    BrApiDetailResponse getStudiesBrApiDetailResponse(String commonCropName, String studyTypeDbId, String programDbId,
                                                      String locationDbId, String seasonDbId, String trialDbId,
                                                      String studyDbId, String active, String sortBy,
                                                      String sortOrder, int page, int pageSize);

    BrApiDetailResponse getStudiesBrApiDetailResponse(String studyDbId, int page, int pageSize);
}
