package org.brapi_igpas.brapi.calls.calls;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CallBuilderTest {
    private Call call;

    @Test
    public void createEmpty() {
        call = new Call.Builder("call").build();

        assertEquals("call", call.getCall());
        assertTrue(call.getDataTypes().isEmpty());
        assertTrue(call.getMethods().isEmpty());
        assertTrue(call.getVersions().isEmpty());
    }

    @Test
    public void createCallWithDataTypes() {
        call = new Call.Builder("call")
                .withDataTypeJson()
                .withDataTypeCsv()
                .build();

        List<String> expectedDataTypes = Arrays.asList(
                Call.Builder.DATATYPE_JSON,
                Call.Builder.DATATYPE_CSV
        );
        assertEquals(expectedDataTypes, call.getDataTypes());
    }

    @Test
    public void createCallWithMethods() {
        call = new Call.Builder("call")
                .withMethodGet()
                .withMethodPost()
                .withMethodPut()
                .withMethodDelete()
                .build();

        List<String> expectedMethods = Arrays.asList(
                Call.Builder.METHOD_GET,
                Call.Builder.METHOD_POST,
                Call.Builder.METHOD_PUT,
                Call.Builder.METHOD_DELETE
        );

        assertEquals(expectedMethods, call.getMethods());
    }

    @Test
    public void createCallWithVersions() {
        call = new Call.Builder("call")
                .withVersionOneZero()
                .withVersionOneOne()
                .withVersionOneTwo()
                .withVersionOneThree()
                .build();

        List<String> expectedVersions = Arrays.asList(
                Call.Builder.VERSION_ONE_ZERO,
                Call.Builder.VERSION_ONE_ONE,
                Call.Builder.VERSION_ONE_TWO,
                Call.Builder.VERSION_ONE_THREE
        );

        assertEquals(expectedVersions, call.getVersions());
    }
}
