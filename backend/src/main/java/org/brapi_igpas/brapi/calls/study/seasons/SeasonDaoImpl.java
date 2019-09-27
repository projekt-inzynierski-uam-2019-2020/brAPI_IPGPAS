package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SeasonDaoImpl implements SeasonDao{
    private final DbValuesFacade dbValuesFacade;
    private List<Value> values;

    @Autowired
    public SeasonDaoImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    public List<Season> getSeasonsForStudyWithStudyId(long studyId) {
        values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start");
        List<Value> valuesWithStudyId = dbValuesFacade.getAllValuesWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values);
        return getSeasonsFromValues(valuesWithStudyId);
    }

    public List<Season> getAll() {
        values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start");
        return getSeasonsFromValues(values);
    }

    private List<Season> getSeasonsFromValues(List<Value> values) {
        List<Season> seasons = new ArrayList<>();
        for (Value value : values) {
            Season season = new Season();
            season.setSeasonDbId(String.valueOf(value.getId()));
            if (value.getValue() != null) {
                season.setSeason(value.getValue());
                season.setYear(value.getValue());
            }
            seasons.add(season);
        }
        return seasons;
    }
}
