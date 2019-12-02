package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.stereotype.Service;

@Service
public class SeasonMapper {

    Season mapToSeason(ValueEntity projection) {
        Season season = new Season();
        season.setSeason(projection.getValue());
        season.setSeasonDbId(String.valueOf(projection.getId()));
        season.setYear(projection.getValue());
        return season;
    }

    Season mapToSeason(SeasonProjection projection) {
        Season season = new Season();
        season.setSeason(projection.getSeason());
        season.setSeasonDbId(projection.getSeasonDbId());
        season.setYear(projection.getYear());
        return season;
    }
}
