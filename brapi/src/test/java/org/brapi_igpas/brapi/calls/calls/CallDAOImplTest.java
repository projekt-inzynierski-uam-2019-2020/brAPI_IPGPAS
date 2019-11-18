package org.brapi_igpas.brapi.calls.calls;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CallDAOImplTest {

    private CallDAOImpl callDAO = new CallDAOImpl();

    @Test
    public void getAllShouldReturnAllAddedCalls() {
        List<Call> actualCalls = callDAO.getAll();
        List<Call> expectedCalls = createExpectedCalls();

        assertNotNull(actualCalls);
        assertEquals(expectedCalls.size(), actualCalls.size());
        assertEquals(expectedCalls, actualCalls);
    }

    private List<Call> createExpectedCalls(){
        List<Call> calls = new ArrayList<>();

        calls.add(new Call.Builder("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        calls.add(new Call.Builder("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        calls.add(new Call.Builder("seasons")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree()
                .build());

        calls.add(new Call.Builder("germplasm")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree()
                .build());

        calls.add(new Call.Builder("studies")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        calls.add(new Call.Builder("trials")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneThree()
                .build());

        return calls;
    }
}
