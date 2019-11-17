package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeasonServiceImplTest {

    // parameters
    private String seasonDbId;
    private String seasonName;
    private String year;
    private int page;
    private int pageSize;

    @Mock
    private SeasonDAO seasonDao;
    @Mock
    private SeasonFilter seasonFilter;
    @Mock
    private PaginationService paginationService;

    @InjectMocks
    private SeasonServiceImpl seasonService;

    @Before
    public void init() {
        page = 0;
        pageSize = 1000;
        seasonDbId = "1012";
        seasonName = "Spring";
        year = "2019";
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndDAOSeasonsWhenNoneOfFilteredParametersIsPresent() {
        List<Season> DAOSeasons = createNSeason(10);
        when(seasonDao.getAll()).thenReturn(DAOSeasons);

        Pagination DAOSeasonPagination = new Pagination(1, 1000, 10, 1);
        when(paginationService.getPagination(DAOSeasons.size(), page, pageSize)).thenReturn(DAOSeasonPagination);
        when(paginationService.paginateList(DAOSeasons, page, pageSize)).thenReturn(DAOSeasons);

        BrAPIDetailResponse actualBrAPIResponse = seasonService.getBrAPIDetailResponse(null, null, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(DAOSeasonPagination, DAOSeasons);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredSeasonsByAllPresentParameters() {
        List<Season> DAOSeasons = createNSeason(10);
        when(seasonDao.getAll()).thenReturn(DAOSeasons);

        List<Season> filteredSeasons = createNSeason(6);
        when(seasonFilter.filterBySeasonDbId(DAOSeasons, seasonDbId)).thenReturn(filteredSeasons);

        List<Season> previousFiltered = filteredSeasons;
        filteredSeasons = createNSeason(4);
        when(seasonFilter.filterBySeasonName(previousFiltered, seasonName)).thenReturn(filteredSeasons);

        previousFiltered = filteredSeasons;
        filteredSeasons = createNSeason(3);
        when(seasonFilter.filterByYear(previousFiltered, year)).thenReturn(filteredSeasons);

        Pagination filteredSeasonPagination = new Pagination(1, 1000, 3, 1);
        when(paginationService.getPagination(filteredSeasons.size(), page, pageSize)).thenReturn(filteredSeasonPagination);
        when(paginationService.paginateList(filteredSeasons, page, pageSize)).thenReturn(filteredSeasons);

        BrAPIDetailResponse actualBrAPIResponse = seasonService.getBrAPIDetailResponse(seasonDbId, seasonName, year, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredSeasonPagination, filteredSeasons);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredSeasonsBySeasonDbIdWhenIsPresentAndOtherParametersAreNull() {
        List<Season> DAOSeasons = createNSeason(10);
        when(seasonDao.getAll()).thenReturn(DAOSeasons);

        List<Season> filteredSeasons = createNSeason(6);
        when(seasonFilter.filterBySeasonDbId(DAOSeasons, seasonDbId)).thenReturn(filteredSeasons);

        Pagination filteredSeasonPagination = new Pagination(1, 1000, 6, 1);
        when(paginationService.getPagination(filteredSeasons.size(), page, pageSize)).thenReturn(filteredSeasonPagination);
        when(paginationService.paginateList(filteredSeasons, page, pageSize)).thenReturn(filteredSeasons);

        BrAPIDetailResponse actualBrAPIResponse = seasonService.getBrAPIDetailResponse(seasonDbId, null, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredSeasonPagination, filteredSeasons);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredSeasonsBySeasonNameWhenIsPresentAndOtherParametersAreNull() {
        List<Season> DAOSeasons = createNSeason(10);
        when(seasonDao.getAll()).thenReturn(DAOSeasons);

        List<Season> filteredSeasons = createNSeason(6);
        when(seasonFilter.filterBySeasonName(DAOSeasons, seasonName)).thenReturn(filteredSeasons);

        Pagination filteredSeasonPagination = new Pagination(1, 1000, 6, 1);
        when(paginationService.getPagination(filteredSeasons.size(), page, pageSize)).thenReturn(filteredSeasonPagination);
        when(paginationService.paginateList(filteredSeasons, page, pageSize)).thenReturn(filteredSeasons);

        BrAPIDetailResponse actualBrAPIResponse = seasonService.getBrAPIDetailResponse(null, seasonName, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredSeasonPagination, filteredSeasons);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredSeasonsByYearWhenIsPresentAndOtherParametersAreNull() {
        List<Season> DAOSeasons = createNSeason(10);
        when(seasonDao.getAll()).thenReturn(DAOSeasons);

        List<Season> filteredSeasons = createNSeason(6);
        when(seasonFilter.filterByYear(DAOSeasons, year)).thenReturn(filteredSeasons);

        Pagination filteredSeasonPagination = new Pagination(1, 1000, 6, 1);
        when(paginationService.getPagination(filteredSeasons.size(), page, pageSize)).thenReturn(filteredSeasonPagination);
        when(paginationService.paginateList(filteredSeasons, page, pageSize)).thenReturn(filteredSeasons);

        BrAPIDetailResponse actualBrAPIResponse = seasonService.getBrAPIDetailResponse(null, null, year, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredSeasonPagination, filteredSeasons);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    private List<Season> createNSeason(int n) {
        return Collections.nCopies(n, new Season());
    }
}
