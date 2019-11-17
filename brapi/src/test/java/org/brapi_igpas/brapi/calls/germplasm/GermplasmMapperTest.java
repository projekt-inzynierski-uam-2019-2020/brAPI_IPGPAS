package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

public class GermplasmMapperTest {

    private GermplasmMapper germplasmMapper;

    @Before
    public void init() {
        germplasmMapper = new GermplasmMapper();
    }

    @Test
    public void mapValuesEntitiesToGermplasmsShouldReturnListOfGermplasmsMappedFromValuesEntities() {
        List<ValuesEntity> givenValuesEntities = createValuesEntities();

        List<Germplasm> actualGermplasms = germplasmMapper.mapValuesEntitiesToGermplasms(givenValuesEntities);
        List<Germplasm> expectedGermplasms = createExpectedGermplasms();

        assertNotNull(actualGermplasms);
        assertEquals(expectedGermplasms, actualGermplasms);
    }

    @Test
    public void mapValuesEntityToGermplasmShouldReturnGermplasmWithMappedFieldsWithValueWhenIsPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValue("value");

        Germplasm actualGermplasm = germplasmMapper.mapValuesEntityToGermplasm(givenValuesEntity);
        Germplasm expectedGermplasm = createExpectedGermplasmWithValue("value");

        assertNotNull(actualGermplasm);
        assertEquals(expectedGermplasm, actualGermplasm);
    }

    @Test
    public void mapValuesEntityToGermplasmShouldReturnGermplasmWithEmptyFieldsWhenValueIsNotPresent() {
        ValuesEntity givenValuesEntity = createValuesEntityWithValue(null);

        Germplasm actualGermplasm = germplasmMapper.mapValuesEntityToGermplasm(givenValuesEntity);
        Germplasm expectedGermplasm = createExpectedGermplasmWithValue(null);

        assertNotNull(actualGermplasm);
        assertEquals(expectedGermplasm, actualGermplasm);
    }

    private List<ValuesEntity> createValuesEntities() {
        return Arrays.asList(
                createValuesEntityWithValue("Germplasm1"),
                createValuesEntityWithValue("Germplasm2"),
                createValuesEntityWithValue(null),
                createValuesEntityWithValue("003112"),
                createValuesEntityWithValue(null),
                createValuesEntityWithValue("I0121")
        );
    }

    private ValuesEntity createValuesEntityWithValue(String value) {
        ValuesEntity valuesEntity = new ValuesEntity();
        valuesEntity.setValue(value);
        return valuesEntity;
    }

    // TODO Agree with client if this is a desirable behaviour (null germplasms)
    private List<Germplasm> createExpectedGermplasms() {
        return Arrays.asList(
                createExpectedGermplasmWithValue("Germplasm1"),
                createExpectedGermplasmWithValue("Germplasm2"),
                createExpectedGermplasmWithValue(null),
                createExpectedGermplasmWithValue("003112"),
                createExpectedGermplasmWithValue(null),
                createExpectedGermplasmWithValue("I0121")
        );
    }

    private Germplasm createExpectedGermplasmWithValue(String value) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmDbId(value);
        germplasm.setAccessionNumber(value);
        germplasm.setDefaultDisplayName(value);
        germplasm.setGermplasmName(value);
        return germplasm;
    }
}
