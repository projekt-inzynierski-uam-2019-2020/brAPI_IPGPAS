package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailResponse;

public interface CommoncropnamesDao {
    BrApiDetailResponse getAll(int page, int pageSize);

    String getCommonCropNameForStudyWithStudyId(long studyId);
}
