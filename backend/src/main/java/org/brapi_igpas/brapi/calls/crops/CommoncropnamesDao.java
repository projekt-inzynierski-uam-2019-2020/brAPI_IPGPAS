package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;

public interface CommoncropnamesDao {
    BrApiDetailPayloadResponse getAll(int page, int pageSize);

    String getCommonCropNameForStudyWithStudyId(long studyId);
}
