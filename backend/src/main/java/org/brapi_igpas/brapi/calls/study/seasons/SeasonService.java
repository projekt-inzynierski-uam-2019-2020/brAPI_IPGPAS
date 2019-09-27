package org.brapi_igpas.brapi.calls.study.seasons;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.FilterUtils.isParameterPresent;

@Service
public class SeasonService {
    private SeasonDao seasonDao;

    public SeasonService(SeasonDao seasonDao) {
        this.seasonDao = seasonDao;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String seasonDbId, String season, String year, int page, int pageSize) {
        List<Season> seasons = seasonDao.getAll();

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
