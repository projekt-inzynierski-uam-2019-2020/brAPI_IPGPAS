package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailResponse;

import java.util.List;

public interface SeasonDao {
    List<Season> getSeasonsForStudyWithStudyId(long studyId);

    BrApiDetailResponse getAll(String seasonDbId, String season, String year, int page, int pageSize);
}
