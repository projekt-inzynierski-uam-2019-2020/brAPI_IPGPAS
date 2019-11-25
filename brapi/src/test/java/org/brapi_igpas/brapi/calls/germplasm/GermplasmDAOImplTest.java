package org.brapi_igpas.brapi.calls.germplasm;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GermplasmDAOImplTest {

    @Mock
    private RepositoryFacade repositoryFacade;
    @Mock
    private GermplasmMapper germplasmMapper;

    @InjectMocks
    private GermplasmDAOImpl germplasmDAO;

    @Test
    public void getAllShouldReturnMappedGermplasmsFromRepository(){
        List<ValuesEntity> valuesEntities = createNValuesEntities(10);
        when(repositoryFacade.getDistinctValuesEntitiesByAttributeDisplayedName("Infraspecific name")).thenReturn(valuesEntities);

        List<Germplasm> germplasms = createNGermplasms(10);
        when(germplasmMapper.mapValuesEntitiesToGermplasms(valuesEntities)).thenReturn(germplasms);

        assertEquals(germplasms, germplasmDAO.getAll());
    }

    private List<Germplasm> createNGermplasms(int n) {
        return new ArrayList<>(Collections.nCopies(n, new Germplasm()));
    }

    private List<ValuesEntity> createNValuesEntities(int n) {
        return new ArrayList<>(Collections.nCopies(n, new ValuesEntity()));
    }
}