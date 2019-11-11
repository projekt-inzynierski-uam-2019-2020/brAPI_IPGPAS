package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeasonMapper {

    public List<Season> mapValuesEntitiesToSeasons(List<ValuesEntity> valuesEntities) {
        List<Season> seasons = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Season season = mapValuesEntityToSeason(valuesEntity);
            seasons.add(season);
        }
        return seasons;
    }

    Season mapValuesEntityToSeason(ValuesEntity valuesEntity) {
        Season season = new Season();
        season.setSeasonDbId(String.valueOf(valuesEntity.getId()));
        Optional<String> value = Optional.ofNullable(valuesEntity.getValue());
        value.ifPresent(season::setSeason);
        value.ifPresent(season::setYear);
        return season;
    }
}
