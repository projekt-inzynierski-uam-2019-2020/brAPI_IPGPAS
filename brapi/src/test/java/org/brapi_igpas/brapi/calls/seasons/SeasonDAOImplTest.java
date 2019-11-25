package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeasonDAOImplTest {

    @Mock
    private RepositoryFacade repositoryFacade;
    @Mock
    private SeasonMapper seasonMapper;

    @InjectMocks
    private SeasonDAOImpl seasonDAO;

    @Test
    public void getAllShouldReturnMappedSeasonsFromRepository() {
        List<ValuesEntity> valuesEntities = createNValuesEntities(10);
        when(repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Study start")).thenReturn(valuesEntities);

        List<Season> seasons = createNSeasons(10);
        when(seasonMapper.mapValuesEntitiesToSeasons(valuesEntities)).thenReturn(seasons);

        assertEquals(seasons, seasonDAO.getAll());
    }

    private List<Season> createNSeasons(int n) {
        return new ArrayList<>(Collections.nCopies(n, new Season()));
    }

    private List<ValuesEntity> createNValuesEntities(int n) {
        return new ArrayList<>(Collections.nCopies(n, new ValuesEntity()));
    }


}
