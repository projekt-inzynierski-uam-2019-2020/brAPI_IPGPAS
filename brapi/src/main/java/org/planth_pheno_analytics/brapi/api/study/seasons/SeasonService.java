package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SeasonService {
    Page<Season> getPagedSeasons(Pageable pageable);
    List<Season> getSeasonsByStudyDbId(Integer studyDbId);
}
