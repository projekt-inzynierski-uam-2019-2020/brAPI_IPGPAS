package org.brapi_igpas.brapi.calls.study.seasons;

import java.util.List;

public interface SeasonDao {
    List<Season> getSeasonsForStudyWithStudyId(long studyId);

    List<Season> getAll();
}
