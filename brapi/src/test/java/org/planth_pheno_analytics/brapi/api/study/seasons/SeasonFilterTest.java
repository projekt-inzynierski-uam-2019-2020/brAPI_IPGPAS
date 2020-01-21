package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

public class SeasonFilterTest {

    private SeasonFilter seasonFilter;

    @Before
    public void init() {
        seasonFilter = new SeasonFilter();
    }

    @Test
    public void filterBySeasonDbIdShouldFilterAllSeasonsWithGivenSeasonDbIdFromGivenStream() {
        // given
        String seasonDbId = "3";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(1, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenSeasonDbIdIsNotPresentInGivenStream() {
        // given
        String seasonDbId = "1215";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String seasonDbId = "3";
        Stream<Season> dataStream = Stream.empty();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenSeasonDbIdIsNull() {
        // given
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonNameShouldFilterAllSeasonsWithGivenSeasonNameFromGivenStream() {
        // given
        String seasonName = "winter";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonName(dataStream, seasonName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterBySeasonNameShouldReturnEmptyStreamWhenGivenSeasonNameIsNotPresentInGivenStream() {
        // given
        String seasonName = "mid-winter";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonName(dataStream, seasonName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonNameShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String seasonName = "summer";
        Stream<Season> dataStream = Stream.empty();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonName(dataStream, seasonName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonNameShouldReturnEmptyStreamWhenGivenSeasonNameIsNull() {
        // given
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterBySeasonName(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }


    @Test
    public void filterByYearShouldFilterAllSeasonsWithGivenYearFromGivenStream() {
        // given
        String year = "2012";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterByYear(dataStream, year);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterByYearShouldReturnEmptyStreamWhenGivenYearIsNotPresentInGivenStream() {
        // given
        String year = "2039";
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterByYear(dataStream, year);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByYearShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String year = "2012";
        Stream<Season> dataStream = Stream.empty();

        // when
        Stream<Season> filteredStream = seasonFilter.filterByYear(dataStream, year);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByYearShouldReturnEmptyStreamWhenGivenYearIsNull() {
        // given
        Stream<Season> dataStream = createDataStream();

        // when
        Stream<Season> filteredStream = seasonFilter.filterByYear(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    private Stream<Season> createDataStream() {
        return Stream.of(
                createSeason("2", "winter", "2012"),
                createSeason("1", "summer", "2012"),
                createSeason("5", "fall", null),
                createSeason("9", "2012", "2015"),
                createSeason("3", "winter", "2010"),
                createSeason("8", "fall", "2012"),
                createSeason("4", "winter", "2011"),
                createSeason("12", null, "2015"),
                createSeason(null, "summer", "2014"),
                createSeason("15", "fall", "2016"));
    }

    // tests data set
    private Season createSeason(String seasonDbId, String seasonName, String year) {
        Season season = new Season();
        season.setSeasonDbId(seasonDbId);
        season.setSeason(seasonName);
        season.setYear(year);
        return season;
    }
}
