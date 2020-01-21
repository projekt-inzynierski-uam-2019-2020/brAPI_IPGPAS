package org.planth_pheno_analytics.brapi.api.study.seasons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeasonMapperTest {

    private SeasonMapper seasonMapper;

    @Before
    public void init(){
        seasonMapper = new SeasonMapper();
    }

    @Test
    public void mapToSeasonShouldMapValuesFromGivenSeasonProjection(){
        // given
        SeasonProjection seasonProjection = mock(SeasonProjection.class);
        when(seasonProjection.getSeasonDbId()).thenReturn("1");
        when(seasonProjection.getSeason()).thenReturn("winter");
        when(seasonProjection.getYear()).thenReturn("2012");

        // when
        Season season = seasonMapper.mapToSeason(seasonProjection);

        // then
        Assert.assertEquals("1", season.getSeasonDbId());
        Assert.assertEquals("winter", season.getSeason());
        Assert.assertEquals("2012", season.getYear());
    }
}
