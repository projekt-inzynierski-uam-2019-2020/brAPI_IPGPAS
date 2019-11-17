package org.brapi_igpas.brapi.calls.seasons;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class SeasonFilter {
    List<Season> filterBySeasonDbId(List<Season> seasons, String seasonDbId) {
        return seasons.stream().filter(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Season> filterBySeasonName(List<Season> seasons, String seasonName) {
        return seasons.stream().filter(season -> areParametersNonNullAndEquals(season.getSeason(), seasonName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Season> filterByYear(List<Season> seasons, String year) {
        return seasons.stream().filter(season -> areParametersNonNullAndEquals(season.getYear(), year))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
