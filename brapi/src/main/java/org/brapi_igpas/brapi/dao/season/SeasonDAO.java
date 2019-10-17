package org.brapi_igpas.brapi.dao.season;

import org.brapi_igpas.brapi.domain.calls.season.Season;

import java.util.List;

public interface SeasonDAO {
    List<Season> getAll();

    List<Season> getSeasonsWithStudyId(long studyId);
}
