package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

class SeasonList extends ListWrapper<Season> {

    SeasonList(List<Season> list) {
        super(list);
    }

    void filterBySeasonDbId(String seasonDbId) {
        list = list.stream().filter(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterBySeasonName(String seasonName) {
        list = list.stream().filter(season -> areParametersNonNullAndEquals(season.getSeason(), seasonName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByYear(String year) {
        list = list.stream().filter(season -> areParametersNonNullAndEquals(season.getYear(), year))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
