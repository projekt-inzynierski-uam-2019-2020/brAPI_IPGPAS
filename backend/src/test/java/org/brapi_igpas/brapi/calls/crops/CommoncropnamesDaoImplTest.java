package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommoncropnamesDaoImplTest {
    @Mock
    private DbValuesFacade dbValuesFacade;
    @InjectMocks
    private CommoncropnamesDaoImpl commoncropnamesDao;

    private List<Value> values;

    @Before
    public void init() {
        values = new ArrayList<>(Arrays.asList(new Value(1, "Tomatillo"), new Value(2, "Hordeum Vulgare"), new Value(3, "Hordeum Vulgare")));
    }

    @Test
    public void getCommonCropNameWithStudyIdShouldReturnCommoncropname() {
        String commoncropname = "Tomatillo";
        Optional<Value> value = Optional.of(new Value(4, commoncropname));
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism")).thenReturn(values);
        when(dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(1, values)).thenReturn(value);
        assertEquals(commoncropname, commoncropnamesDao.getCommonCropNameWithStudyId(1));
    }

    @Test
    public void getCommonCropNameWithStudyIdShouldReturnEmptyString() {
        Optional<Value> value = Optional.empty();
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism")).thenReturn(values);
        when(dbValuesFacade.getValueWithStudyIdFromValuesWithAttributeDisplayedName(1, values)).thenReturn(value);
        assertEquals("", commoncropnamesDao.getCommonCropNameWithStudyId(1));
    }

    @Test
    public void getAllShouldReturnDistinctCommoncropnames() {
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism")).thenReturn(values);
        List<String> commoncropnames = Arrays.asList("Tomatillo", "Hordeum Vulgare");
        assertEquals(commoncropnames, commoncropnamesDao.getAll());
    }

    @Test
    public void getAllShouldSkipNullValueAndReturnDistinctCommoncropnames() {
        values.add(new Value(4, null));
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism")).thenReturn(values);
        List<String> commoncropnames = Arrays.asList("Tomatillo", "Hordeum Vulgare");
        assertEquals(commoncropnames, commoncropnamesDao.getAll());
    }
}
