package org.brapi_igpas.brapi.calls.germplasm.germplasm;

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

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GermplasmDaoImplTest {
    @Mock
    private DbValuesFacade dbValuesFacade;

    @InjectMocks
    private GermplasmDaoImpl germplasmDao;

    private List<Value> values;

    @Before
    public void init() {
        values = new ArrayList<>(Arrays.asList(new Value(1, "Name001"), new Value(2, "Name002")));
    }

    @Test
    public void getAllShouldReturnAllSeasons() {
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Infraspecific name")).thenReturn(values);
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmDbId("1");
        germplasm.setGermplasmName("Name001");
        germplasm.setDefaultDisplayName("Name001");
        germplasm.setAccessionNumber("Name001");

        Germplasm secGermplasm = new Germplasm();
        secGermplasm.setGermplasmDbId("2");
        secGermplasm.setGermplasmName("Name002");
        secGermplasm.setDefaultDisplayName("Name002");
        secGermplasm.setAccessionNumber("Name002");

        List<Germplasm> germplasms = Arrays.asList(germplasm, secGermplasm);
        assertEquals(germplasms, germplasmDao.getAll());
    }
}
