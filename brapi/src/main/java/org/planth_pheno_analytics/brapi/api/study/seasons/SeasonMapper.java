package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.springframework.stereotype.Service;

@Service
public class SeasonMapper {

    Season mapToSeason(SeasonProjection projection) {
        Season season = new Season();
        season.setSeason(projection.getSeason());
        season.setSeasonDbId(projection.getSeasonDbId());
        season.setYear(projection.getYear());
        return season;
    }
}
