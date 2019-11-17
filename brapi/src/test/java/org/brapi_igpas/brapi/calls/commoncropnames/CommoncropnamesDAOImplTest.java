package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.brapi_igpas.igpas.repository.RepositoryFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommoncropnamesDAOImplTest {

    @Mock
    private RepositoryFacade repositoryFacade;
    @Mock
    private CommoncropnamesMapper commoncropnamesMapper;

    @InjectMocks
    private CommoncropnamesDAOImpl commoncropnamesDAO;

    @Test
    public void getAllShouldReturnMappedCommoncropnamesFromRepositoryValuesEntities() {
        List<ValuesEntity> valuesEntities = createValuesEntities();
        when(repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Organism")).thenReturn(valuesEntities);

        List<String> commoncropnames = Arrays.asList("Tomatillo", "Hordeum Vulgare");
        when(commoncropnamesMapper.mapValuesEntitiesToCommoncropnames(valuesEntities)).thenReturn(commoncropnames);

        assertEquals(commoncropnames, commoncropnamesDAO.getAll());
    }

    private List<ValuesEntity> createValuesEntities() {
        return Arrays.asList(createValuesEntityWithValue("Tomatillo"), createValuesEntityWithValue("Hordeum Vulgare"));
    }

    private ValuesEntity createValuesEntityWithValue(String value) {
        ValuesEntity valuesEntity = new ValuesEntity();
        valuesEntity.setValue(value);
        return valuesEntity;
    }
}
