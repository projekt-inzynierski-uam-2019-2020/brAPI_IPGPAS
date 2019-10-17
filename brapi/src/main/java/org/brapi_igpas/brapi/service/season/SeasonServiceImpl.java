package org.brapi_igpas.brapi.service.season;

import org.brapi_igpas.brapi.dao.season.SeasonDAO;
import org.brapi_igpas.brapi.domain.calls.season.Season;
import org.brapi_igpas.brapi.domain.calls.season.SeasonList;
import org.brapi_igpas.brapi.domain.response.BrApiDetailResponse;
import org.brapi_igpas.brapi.domain.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginatedList;
import static org.brapi_igpas.brapi.utils.PaginationUtils.getPaginationInfo;
import static org.brapi_igpas.brapi.validator.ParameterValidator.isParameterPresent;

@Service
public class SeasonServiceImpl implements SeasonService{
    private SeasonDAO seasonDAO;

    public SeasonServiceImpl(SeasonDAO seasonDAO) {
        this.seasonDAO = seasonDAO;
    }

    public BrApiDetailResponse getBrApiDetailResponse(String seasonDbId, String season, String year, int page, int pageSize) {
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

        Pagination paginationInfo = getPaginationInfo(seasonList.getSize(), page, pageSize);
        List<Season> seasons = getPaginatedList(seasonList.getList(), page, pageSize);

        return new BrApiDetailResponse(paginationInfo, seasons);
    }
}
