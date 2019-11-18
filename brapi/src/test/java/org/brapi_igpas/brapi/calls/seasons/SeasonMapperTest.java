package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

public class SeasonMapperTest {

    private SeasonMapper seasonMapper;

    @Before
    public void init() {
        seasonMapper = new SeasonMapper();
    }

    @Test
    public void mapValuesEntitiesToSeasonsShouldReturnListOfSeasonsMappedFromValuesEntities() {
        List<ValuesEntity> givenValuesEntities = createValuesEntities();

        List<Season> actualSeasons = seasonMapper.mapValuesEntitiesToSeasons(givenValuesEntities);
        List<Season> expectedSeasons = createExpectedSeasons();

        assertNotNull(actualSeasons);
        assertEquals(expectedSeasons, actualSeasons);
    }

    @Test
    public void mapValuesEntityToSeasonShouldReturnSeasonWithMappedFieldsWithValueWhenIsPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValueAndId("value", 3);

        Season actualSeason = seasonMapper.mapValuesEntityToSeason(givenValuesEntity);
        Season expectedSeason = createExpectedSeasonWithValueAndId("value", "3");

        assertNotNull(actualSeason);
        assertEquals(expectedSeason, actualSeason);
    }

    @Test
    public void mapValuesEntityToSeasonShouldReturnSeasonWithEmptyFieldsWhenValueIsNotPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValueAndId(null, 1);

        Season actualSeason = seasonMapper.mapValuesEntityToSeason(givenValuesEntity);
        Season expectedSeason = createExpectedSeasonWithValueAndId(null, "1");

        assertNotNull(actualSeason);
        assertEquals(expectedSeason, actualSeason);
    }

    private List<ValuesEntity> createValuesEntities() {
        return Arrays.asList(
                createValuesEntityWithValueAndId("Season", 1),
                createValuesEntityWithValueAndId("Winter", 2),
                createValuesEntityWithValueAndId(null, 3),
                createValuesEntityWithValueAndId("Summer", 4),
                createValuesEntityWithValueAndId(null, 5),
                createValuesEntityWithValueAndId("2017", 6)
        );
    }

    private ValuesEntity createValuesEntityWithValueAndId(String value, long id) {
        ValuesEntity valuesEntity = new ValuesEntity();
        valuesEntity.setId(id);
        valuesEntity.setValue(value);
        return valuesEntity;
    }

    private List<Season> createExpectedSeasons() {
        return Arrays.asList(
                createExpectedSeasonWithValueAndId("Season", "1"),
                createExpectedSeasonWithValueAndId("Winter", "2"),
                createExpectedSeasonWithValueAndId(null, "3"),
                createExpectedSeasonWithValueAndId("Summer", "4"),
                createExpectedSeasonWithValueAndId(null, "5"),
                createExpectedSeasonWithValueAndId("2017", "6")
        );
    }

    private Season createExpectedSeasonWithValueAndId(String value, String valueId) {
        Season season = new Season();
        season.setSeasonDbId(valueId);
        season.setSeason(value);
        season.setYear(value);
        return season;
    }
}
