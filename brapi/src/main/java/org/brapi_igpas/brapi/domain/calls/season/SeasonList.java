package org.brapi_igpas.brapi.domain.calls.season;

import org.brapi_igpas.brapi.domain.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

public class SeasonList extends ListWrapper<Season> {

    public SeasonList(List<Season> list) {
        super(list);
    }

    public void filterBySeasonDbId(String seasonDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getSeasonDbId(), seasonDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterBySeasonName(String seasonName) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getSeason(), seasonName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByYear(String year) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getYear(), year))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
