package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

public class CommoncropnamesMapperTest {

    private CommoncropnamesMapper commoncropnamesMapper;

    @Before
    public void init() {
        commoncropnamesMapper = new CommoncropnamesMapper();
    }

    @Test
    public void mapValuesEntitiesToCommoncropnamesShouldReturnListOfStringsWithPresentValuesFromValuesEntities() {
        List<ValuesEntity> givenValuesEntities = createValuesEntities();

        List<String> actualCommoncropnames = commoncropnamesMapper.mapValuesEntitiesToCommoncropnames(givenValuesEntities);
        List<String> expectedCommoncropnames = Arrays.asList("Tomatillo", "HordeumVulgare", "Crop", "Rye");

        assertNotNull(actualCommoncropnames);
        assertEquals(expectedCommoncropnames, actualCommoncropnames);
    }

    @Test
    public void mapValuesEntityToCommoncropnameShouldReturnOptionalStringWithMappedValueWhenIsPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValue("Tomatillo");

        Optional<String> actualCommoncropname = commoncropnamesMapper.mapValuesEntityToCommoncropname(givenValuesEntity);
        Optional<String> expectedCommoncropname = Optional.of("Tomatillo");

        assertTrue(actualCommoncropname.isPresent());
        assertEquals(expectedCommoncropname.get(), actualCommoncropname.get());
    }

    @Test
    public void mapValuesEntityToCommoncropnameShouldReturnOptionalEmptyStringWhenValueIsNotPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValue(null);

        Optional<String> actualCommoncropname = commoncropnamesMapper.mapValuesEntityToCommoncropname(givenValuesEntity);

        assertFalse(actualCommoncropname.isPresent());
    }

    private List<ValuesEntity> createValuesEntities() {
        return Arrays.asList(
                createValuesEntityWithValue("Tomatillo"),
                createValuesEntityWithValue("HordeumVulgare"),
                createValuesEntityWithValue(null),
                createValuesEntityWithValue("Crop"),
                createValuesEntityWithValue(null),
                createValuesEntityWithValue("Rye")
        );
    }

    private ValuesEntity createValuesEntityWithValue(String value) {
        ValuesEntity valuesEntity = new ValuesEntity();
        valuesEntity.setValue(value);
        return valuesEntity;
    }
}
