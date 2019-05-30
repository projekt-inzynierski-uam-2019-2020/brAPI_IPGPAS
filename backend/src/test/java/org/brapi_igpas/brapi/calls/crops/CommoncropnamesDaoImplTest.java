package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.igpas.entity.Value;
import org.brapi_igpas.igpas.service.DbValuesFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommoncropnamesDaoImplTest {
    // default parameters
    private int page = 0;
    private int pageSize = 1000;

    private CommoncropnamesDao commoncropnamesDao;
    private DbValuesFacade dbValuesFacade;

    private List<String> commoncropnames;
    private List<Value> values;


    @Before
    public void init() {
        commoncropnames = new ArrayList<>();
        commoncropnames.add("Lorem");
        commoncropnames.add("Ipsum");
        commoncropnames.add("Ipsum");
        commoncropnames.add("Ipsum");

        dbValuesFacade = mock(DbValuesFacade.class); // the order is important!
        values = getSampleValueListWithCommoncropnames(commoncropnames);
        when(dbValuesFacade.getAllValuesWithAttributeDisplayedName("Organism")).thenReturn(values);

        commoncropnamesDao = new CommoncropnamesDaoImpl(dbValuesFacade);
    }

    @Test
    public void getCommonCropNameForStudyWithStudyIdShouldReturnCommoncropname() {
        final long studyId = 1;
        String commoncropname = "Tomatillo";
        Optional<Value> value = Optional.of(new Value(1, commoncropname));
        when(dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)).thenReturn(value);
        assertEquals(commoncropname, commoncropnamesDao.getCommonCropNameForStudyWithStudyId(studyId));
    }

    @Test
    public void getCommonCropNameForStudyWithStudyIdShouldReturnEmptyString() {
        final long studyId = 1;
        Optional<Value> value = Optional.empty();
        when(dbValuesFacade.getFirstValueWithStudyIdFromValuesWithAttributeDisplayedName(studyId, values)).thenReturn(value);
        assertEquals("", commoncropnamesDao.getCommonCropNameForStudyWithStudyId(studyId));
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithAllCommoncropnames() {
        commoncropnames = commoncropnames.stream().distinct().collect(Collectors.toList());
        assertEquals(commoncropnames, commoncropnamesDao.getAll(page, pageSize).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCallsFromGivenPage() {
        commoncropnames = commoncropnames.stream().distinct().collect(Collectors.toList());
        assertEquals(commoncropnames.subList(0, 1), commoncropnamesDao.getAll(0, 1).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithEmptyList() {
        assertEquals(Collections.emptyList(), commoncropnamesDao.getAll(20, 5).getResult().getData());
    }

    private List<Value> getSampleValueListWithCommoncropnames(List<String> commoncropnames) {
        List<Value> values = new ArrayList<>();
        for (int i = 0; i < commoncropnames.size(); i++) {
            Value value = new Value(i, commoncropnames.get(i));
            values.add(value);
        }
        return values;
    }
}
