package org.brapi_igpas.brapi.calls.seasons;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.*;

public class SeasonFilterTest {

    private List<Season> seasons;
    private SeasonFilter seasonFilter;

    @Before
    public void init() {
        seasons = new ArrayList<>();
        seasonFilter = new SeasonFilter();
    }

    @Test
    public void filterBySeasonDbIdShouldReturnFilteredListByGivenDbIdWhenExists() {
        seasons.addAll(createNSeasonsWithSeasonDbId(8, "1251"));
        seasons.addAll(createNSeasonsWithSeasonDbId(10, "351"));

        seasons = seasonFilter.filterBySeasonDbId(seasons, "351");

        assertNotNull(seasons);
        assertEquals(10, seasons.size());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyListWhenDbIdDoesNotExist() {
        seasons.addAll(createNSeasonsWithSeasonDbId(8, "1251"));
        seasons.addAll(createNSeasonsWithSeasonDbId(10, "351"));

        seasons = seasonFilter.filterBySeasonDbId(seasons, "125");

        assertNotNull(seasons);
        assertTrue(seasons.isEmpty());
    }

    @Test
    public void filterBySeasonNameShouldReturnFilteredListByGivenNameWhenExists() {
        seasons.addAll(createNSeasonsWithSeasonName(2, "Winter"));
        seasons.addAll(createNSeasonsWithSeasonName(5, "Summer"));

        seasons = seasonFilter.filterBySeasonName(seasons, "Summer");

        assertNotNull(seasons);
        assertEquals(5, seasons.size());
    }

    @Test
    public void filterBySeasonNameShouldReturnEmptyListWhenSeasonNameDoesNotExist() {
        seasons.addAll(createNSeasonsWithSeasonName(3, "Winter"));
        seasons.addAll(createNSeasonsWithSeasonName(4, "Summer"));

        seasons = seasonFilter.filterBySeasonName(seasons, "Fall");

        assertNotNull(seasons);
        assertTrue(seasons.isEmpty());
    }

    @Test
    public void filterByYearShouldReturnFilteredListByGivenYearWhenExists() {
        seasons.addAll(createNSeasonsWithYear(4, "2017"));
        seasons.addAll(createNSeasonsWithYear(3, "2012"));

        seasons = seasonFilter.filterByYear(seasons, "2017");

        assertNotNull(seasons);
        assertEquals(4, seasons.size());
    }

    @Test
    public void filterByYearShouldReturnEmptyListWhenYearDoesNotExist() {
        seasons.addAll(createNSeasonsWithYear(4, "2017"));
        seasons.addAll(createNSeasonsWithYear(3, "2012"));

        seasons = seasonFilter.filterByYear(seasons, "2015");

        assertNotNull(seasons);
        assertTrue(seasons.isEmpty());
    }

    private List<Season> createNSeasonsWithSeasonDbId(int n, String seasonDbId) {
        return Collections.nCopies(n, createSeasonWithSeasonDbId(seasonDbId));
    }

    private Season createSeasonWithSeasonDbId(String seasonDbId) {
        Season season = new Season();
        season.setSeasonDbId(seasonDbId);
        return season;
    }

    private List<Season> createNSeasonsWithSeasonName(int n, String seasonName) {
        return Collections.nCopies(n, createSeasonWithSeasonName(seasonName));
    }

    private Season createSeasonWithSeasonName(String seasonName) {
        Season season = new Season();
        season.setSeason(seasonName);
        return season;
    }

    private List<Season> createNSeasonsWithYear(int n, String year) {
        return Collections.nCopies(n, createSeasonWithYear(year));
    }

    private Season createSeasonWithYear(String year) {
        Season season = new Season();
        season.setYear(year);
        return season;
    }
}
