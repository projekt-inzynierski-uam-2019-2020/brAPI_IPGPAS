package org.brapi_igpas.brapi.calls.calls;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class CallFilterTest {

    private List<Call> calls;
    private CallFilter callFilter;

    @Before
    public void init() {
        calls = new ArrayList<>();
        callFilter = new CallFilter();
    }

    @Test
    public void filterByDataTypeShouldReturnFilteredListByGivenDataTypeJsonExample() {
        calls.addAll(createNCallsWithDataTypeCsv(15));
        calls.addAll(createNCallsWithDataTypeJson(10));

        calls = callFilter.filterByDataType(calls, Call.Builder.DATATYPE_JSON);

        assertNotNull(calls);
        assertEquals(10, calls.size());
    }

    @Test
    public void filterByDataTypeShouldReturnFilteredListByGivenDataTypeCsvExample() {
        calls.addAll(createNCallsWithDataTypeCsv(12));
        calls.addAll(createNCallsWithDataTypeJson(7));

        calls = callFilter.filterByDataType(calls, Call.Builder.DATATYPE_CSV);

        assertNotNull(calls);
        assertEquals(12, calls.size());
    }

    @Test
    public void filterByDataTypeShouldReturnEmptyListWhenDatTypeDoesNotExist() {
        calls.addAll(createNCallsWithDataTypeCsv(5));
        calls.addAll(createNCallsWithDataTypeJson(3));

        calls = callFilter.filterByDataType(calls, "xslx");

        assertNotNull(calls);
        assertTrue(calls.isEmpty());
    }

    private List<Call> createNCallsWithDataTypeJson(int n) {
        return Collections.nCopies(n, new Call.Builder("call")
                .withDataTypeJson()
                .build());
    }

    private List<Call> createNCallsWithDataTypeCsv(int n) {
        return Collections.nCopies(n, new Call.Builder("call")
                .withDataTypeCsv()
                .build());
    }
}
