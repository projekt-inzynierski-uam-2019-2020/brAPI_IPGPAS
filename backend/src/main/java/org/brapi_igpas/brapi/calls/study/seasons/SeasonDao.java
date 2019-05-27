package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.PaginationUtils;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SeasonDao {
    private final DbValuesFacade dbValuesFacade;

    @Autowired
    public SeasonDao(DbValuesFacade dbValuesFacade) {
        this.dbValuesFacade = dbValuesFacade;
    }

    public BrApiDetailPayloadResponse getAll(String seasonDbId, String season, String year, int page, int pageSize) {
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Study start");

        List<Season> seasons = initializeSeasonsWithDbValues(values);

        if (isParameterPresent(seasonDbId)) {
            seasons = getSeasonWithSeasonDbId(seasons, seasonDbId);
        }

        if (isParameterPresent(season)) {
            seasons = getSeasonWithSeasonName(seasons, season);
        }

        if (isParameterPresent(year)) {
            seasons = getSeasonWithYear(seasons, year);
        }

        Pagination paginationInfo = PaginationUtils.getPaginationInfo(seasons.size(), page, pageSize);
        int fromIndex = PaginationUtils.getFromIndex(seasons.size(), page, pageSize);
        int toIndex = PaginationUtils.getToIndex(seasons.size(), page, pageSize);
        seasons = seasons.subList(fromIndex, toIndex);

        return new BrApiDetailPayloadResponse(seasons, paginationInfo);
    }

    public List<Season> getSeasonsForStudyWithStudyId(long studyId){
        List<Value> values = dbValuesFacade.getAllValuesByAttributeDisplayedName("Study start");
        values = values.stream().filter(v -> v.getStudyId() == studyId).collect(Collectors.toCollection(ArrayList::new));
        return initializeSeasonsWithDbValues(values);
    }

    private List<Season> initializeSeasonsWithDbValues(List<Value> values) {
        return values.stream().map(temp -> {
            Season season = new Season();
            season.setSeason(temp.getValue());
            season.setYear(temp.getValue());
            season.setSeasonDbId(String.valueOf(temp.getId()));
            return season;
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isParameterPresent(String parameter) {
        return parameter != null && !parameter.isEmpty();
    }

    private List<Season> getSeasonWithSeasonDbId(List<Season> seasons, String seasonDbId) {
        return seasons.stream().filter(s -> s.getSeasonDbId() != null && s.getSeasonDbId().equals(seasonDbId)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Season> getSeasonWithSeasonName(List<Season> seasons, String seasonName) {
        return seasons.stream().filter(s -> s.getSeason() != null && s.getSeason().equals(seasonName)).collect(Collectors.toCollection(ArrayList::new));
    }

    private List<Season> getSeasonWithYear(List<Season> seasons, String year) {
        return seasons.stream().filter(s -> s.getYear() != null && s.getYear().equals(year)).collect(Collectors.toCollection(ArrayList::new));
    }
}
