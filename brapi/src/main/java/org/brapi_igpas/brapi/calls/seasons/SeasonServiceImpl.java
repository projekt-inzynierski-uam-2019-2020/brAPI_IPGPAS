package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonDAO seasonDAO;
    private final SeasonFilter seasonFilter;
    private final PaginationService paginationService;

    public SeasonServiceImpl(SeasonDAO seasonDAO, SeasonFilter seasonFilter, PaginationService paginationService) {
        this.seasonDAO = seasonDAO;
        this.seasonFilter = seasonFilter;
        this.paginationService = paginationService;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String seasonDbId, String season, String year, int page, int pageSize) {
        List<Season> seasons = seasonDAO.getAll();

        if (isParameterPresent(seasonDbId)) {
            seasons = seasonFilter.filterBySeasonDbId(seasons, seasonDbId);
        }

        if (isParameterPresent(season)) {
            seasons = seasonFilter.filterBySeasonName(seasons, season);
        }

        if (isParameterPresent(year)) {
            seasons = seasonFilter.filterByYear(seasons, year);
        }

        Pagination paginationInfo = paginationService.getPagination(seasons.size(), page, pageSize);
        seasons = paginationService.paginateList(seasons, page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, seasons);
    }
}
