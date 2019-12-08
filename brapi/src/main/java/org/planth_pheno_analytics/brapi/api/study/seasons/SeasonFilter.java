package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class SeasonFilter {

    Stream<Season> filterBySeasonDbId(Stream<Season> seasonStream, String seasonDbId) {
        return seasonStream.filter(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId));
    }

    Stream<Season> filterBySeasonName(Stream<Season> seasonStream, String seasonName) {
        return seasonStream.filter(season -> areParametersNonNullAndEquals(season.getSeason(), seasonName));
    }

    Stream<Season> filterByYear(Stream<Season> seasonStream, String year) {
        return seasonStream.filter(season -> areParametersNonNullAndEquals(season.getYear(), year));
    }
}
