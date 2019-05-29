package org.brapi_igpas.brapi.calls.study.studies;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;

public interface StudyDao {
    BrApiDetailPayloadResponse getAll(String commonCropName, String studyTypeDbId, String programDbId,
                                      String locationDbId, String seasonDbId, String trialDbId,
                                      String studyDbId, String active, String sortBy,
                                      String sortOrder, int page, int pageSize);
}
