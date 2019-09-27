package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.calls.study.seasons.Season;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonDao;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonService;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeasonServiceTest {
    @Mock
    private SeasonDao seasonDao;

    @InjectMocks
    private SeasonService seasonService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsInResultData() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 2, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(seasons, pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithSeasonEqualSummerInResultData() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(seasons.get(0)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, "Summer", null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithSeasonDbIdEqual2InResultData() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(seasons.get(1)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse("2", null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithSeasonYearEqual2012InResultData() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(seasons.get(0)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, null, "2012", 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithEmptyListInResultDataWhenSeasonIsNotPresent() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, "Fall", null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithEmptyListInResultDataWhenSeasonDbIdIsNotPresent() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse("15", null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithSeasonsWithEmptyListInResultDataWhenSeasonYearIsNotPresent() {
        List<Season> seasons = Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013"));

        when(seasonDao.getAll()).thenReturn(seasons);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, null, "2016", 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithEmptyDataInResult() {
        when(seasonDao.getAll()).thenReturn(Collections.emptyList());

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = seasonService.getBrApiDetailResponse(null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }
}
