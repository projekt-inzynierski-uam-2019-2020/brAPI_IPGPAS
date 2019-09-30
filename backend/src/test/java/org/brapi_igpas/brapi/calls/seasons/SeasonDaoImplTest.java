package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.calls.study.seasons.Season;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonDaoImpl;
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
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeasonDaoImplTest {
    @Mock
    private DbValuesFacade dbValuesFacade;

    @InjectMocks
    private SeasonDaoImpl seasonDao;

    private List<Value> values;

    @Before
    public void init() {
        values = new ArrayList<>(Arrays.asList(new Value(1, "2012"), new Value(2, "2012"), new Value(3, "2013")));
    }

    @Test
    public void getSeasonsWithStudyIdShouldReturnSeasons() {
        List<Season> seasons = new ArrayList<>(Arrays.asList(new Season("2012", "1", "2012"),
                new Season("2012", "2", "2012")));

        List<Value> valuesWithAttributeDisplayedName = new ArrayList<>(Arrays.asList(new Value(1, "2012"),
                new Value(2, "2012")));

        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start")).thenReturn(values);
        when(dbValuesFacade.getAllValuesWithStudyIdFromValuesWithAttributeDisplayedName(1, values)).thenReturn(valuesWithAttributeDisplayedName);
        assertEquals(seasons, seasonDao.getSeasonsForStudyWithStudyId(1));
    }

    @Test
    public void getSeasonsWithStudyIdShouldReturnEmptyList() {
        List<Value> valuesWithAttributeDisplayedName = Collections.emptyList();

        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start")).thenReturn(values);
        when(dbValuesFacade.getAllValuesWithStudyIdFromValuesWithAttributeDisplayedName(1, values)).thenReturn(valuesWithAttributeDisplayedName);
        assertEquals(Collections.emptyList(), seasonDao.getSeasonsForStudyWithStudyId(1));
    }

    @Test
    public void getAllShouldReturnAllSeasons() {
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Study start")).thenReturn(values);
        List<Season> seasons = new ArrayList<>(Arrays.asList(new Season("2012", "1", "2012"),
                new Season("2012", "2", "2012"), new Season("2013", "3", "2013")));
        assertEquals(seasons, seasonDao.getAll());
    }
}
