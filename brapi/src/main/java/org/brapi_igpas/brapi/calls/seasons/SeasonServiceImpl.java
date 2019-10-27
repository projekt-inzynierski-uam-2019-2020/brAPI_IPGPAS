package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonDAO seasonDAO;

    public SeasonServiceImpl(SeasonDAO seasonDAO) {
        this.seasonDAO = seasonDAO;
    }

    public BrAPIDetailResponse getBrAPIDetailResponse(String seasonDbId, String season, String year, int page, int pageSize) {
        SeasonList seasonList = new SeasonList(seasonDAO.getAll());

        if (isParameterPresent(seasonDbId)) {
            seasonList.filterBySeasonDbId(seasonDbId);
        }

        if (isParameterPresent(season)) {
            seasonList.filterBySeasonName(season);
        }

        if (isParameterPresent(year)) {
            seasonList.filterByYear(year);
        }

        Pagination paginationInfo = getPaginationInfo(seasonList.size(), page, pageSize);
        List<Season> seasons = getPaginatedList(seasonList.getList(), page, pageSize);

        return new BrAPIDetailResponse(paginationInfo, seasons);
    }
}
