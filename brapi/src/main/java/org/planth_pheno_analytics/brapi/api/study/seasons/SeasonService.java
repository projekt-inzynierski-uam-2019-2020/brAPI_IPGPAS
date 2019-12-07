package org.planth_pheno_analytics.brapi.api.study.seasons;

import java.util.List;


public interface SeasonService {
    List<Season> getFilteredSeasons(SeasonCriteria seasonCriteria);
    List<Season> getSeasonsByStudyDbId(Integer studyDbId);
}
