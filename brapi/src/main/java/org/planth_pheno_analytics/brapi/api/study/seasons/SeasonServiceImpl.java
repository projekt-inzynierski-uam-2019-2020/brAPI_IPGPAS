package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonProjectionResources seasonProjectionResources;
    private final SeasonMapper seasonMapper;
    private final SeasonFilter seasonFilter;

    public SeasonServiceImpl(SeasonProjectionResources seasonProjectionResources, SeasonMapper seasonMapper,
                             SeasonFilter seasonFilter) {
        this.seasonProjectionResources = seasonProjectionResources;
        this.seasonMapper = seasonMapper;
        this.seasonFilter = seasonFilter;
    }

    @Override
    public List<Season> getFilteredSeasons(SeasonCriteria seasonCriteria) {
        Stream<Season> seasonStream = seasonProjectionResources.getSeasons().stream()
                .map(seasonMapper::mapToSeason);

        String seasonDbId = seasonCriteria.getSeasonDbId();
        if (isParameterPresent(seasonDbId)) {
            seasonStream = seasonFilter.filterBySeasonDbId(seasonStream, seasonDbId);
        }
        String season = seasonCriteria.getSeason();
        if (isParameterPresent(season)) {
            seasonStream = seasonFilter.filterBySeasonName(seasonStream, season);
        }
        String year = seasonCriteria.getYear();
        if (isParameterPresent(year)) {
            seasonStream = seasonFilter.filterByYear(seasonStream, year);
        }

        return seasonStream.collect(Collectors.toList());
    }

    @Override
    public List<Season> getSeasonsByStudyDbId(Integer studyDbId) {
        return seasonProjectionResources.getSeasonsByStudyId(studyDbId).stream()
                .map(seasonMapper::mapToSeason)
                .collect(Collectors.toList());
    }
}
