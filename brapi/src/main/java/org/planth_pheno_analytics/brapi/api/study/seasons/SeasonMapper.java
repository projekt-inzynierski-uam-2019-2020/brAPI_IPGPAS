package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.planth_pheno_analytics.data.entity.ValueEntity;
import org.springframework.stereotype.Service;

@Service
public class SeasonMapper {

    Season mapToSeason(ValueEntity entity) {
        Season season = new Season();
        season.setSeason(entity.getValue());
        season.setSeasonDbId(String.valueOf(entity.getId()));
        season.setYear(entity.getValue());
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
