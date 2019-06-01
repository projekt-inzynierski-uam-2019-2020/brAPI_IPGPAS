package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Repository
public class SeasonDaoImpl implements SeasonDao{
    private final DbValuesFacade dbValuesFacade;
    private List<Value> values;

    @Autowired
    public SeasonDaoImpl(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
        this.values = dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start");
    }

    public List<Season> getSeasonsForStudyWithStudyId(long studyId) {
        List<Value> valuesWithStudyId = dbValuesFacade.getAllValuesWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values);
        return getSeasonsFromValues(valuesWithStudyId);
    }

    public BrApiDetailResponse getAll(String seasonDbId, String season, String year, int page, int pageSize) {
        List<Season> seasons = getSeasonsFromValues(values);

        if (isParameterPresent(seasonDbId)) {
            seasons = getSeasonsWithSeasonDbId(seasons, seasonDbId);
        }

        if (isParameterPresent(season)) {
            seasons = getSeasonsWithSeasonName(seasons, season);
        }

        if (isParameterPresent(year)) {
            seasons = getSeasonsWithYear(seasons, year);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(seasons.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(seasons.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(seasons.size(), page, pageSize);
        seasons = seasons.subList(fromIndex, toIndex);

        return new BrApiDetailResponse(seasons, paginationInfo);
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

    private List<Season> getSeasonsWithSeasonDbId(List<Season> seasons, String seasonDbId) {
        return seasons.stream().filter(s -> s.getSeasonDbId() != null && s.getSeasonDbId().equals(seasonDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Season> getSeasonsWithSeasonName(List<Season> seasons, String seasonName) {
        return seasons.stream().filter(s -> s.getSeason() != null && s.getSeason().equals(seasonName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Season> getSeasonsWithYear(List<Season> seasons, String year) {
        return seasons.stream().filter(s -> s.getYear() != null && s.getYear().equals(year))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
