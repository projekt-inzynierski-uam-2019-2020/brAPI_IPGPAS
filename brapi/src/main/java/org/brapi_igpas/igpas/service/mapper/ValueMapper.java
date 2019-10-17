package org.brapi_igpas.igpas.service.mapper;

import org.brapi_igpas.brapi.domain.calls.germplasm.Germplasm;
import org.brapi_igpas.brapi.domain.calls.season.Season;
import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValueMapper {

    public List<String> mapToCommoncropnames(List<ValuesEntity> valuesEntities) {
        List<String> commoncropnames = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Optional<String> value = Optional.ofNullable(valuesEntity.getValue());
            value.ifPresent(commoncropnames::add);
        }
        return commoncropnames;
    }

    public List<Season> mapToSeasons(List<ValuesEntity> valuesEntities) {
        List<Season> seasons = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Season season = new Season();
            season.setSeasonDbId(String.valueOf(valuesEntity.getId()));
            Optional<String> value = Optional.ofNullable(valuesEntity.getValue());
            value.ifPresent(season::setSeason);
            value.ifPresent(season::setYear);
            seasons.add(season);
        }
        return seasons;
    }

    public List<Germplasm> mapToGermplasms(List<ValuesEntity> valuesEntities) {
        List<Germplasm> germplasms = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Germplasm germplasm = new Germplasm();
            Optional<String> value = Optional.ofNullable(valuesEntity.getValue());
            value.ifPresent(germplasm::setGermplasmDbId);
            value.ifPresent(germplasm::setAccessionNumber);
            value.ifPresent(germplasm::setDefaultDisplayName);
            value.ifPresent(germplasm::setGermplasmName);
            germplasms.add(germplasm);
        }
        return germplasms;
    }
}
