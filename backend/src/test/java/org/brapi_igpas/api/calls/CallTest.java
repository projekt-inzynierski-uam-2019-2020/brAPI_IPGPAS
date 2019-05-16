package org.brapi_igpas.api.calls;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class CallTest {
    private Call call;

    @Before
    public void init(){
        call = new Call();
    }

    @Test
    public void create(){
        assertNull(call.getCall());
        assertTrue(call.getDataTypes().isEmpty());
        assertTrue(call.getMethods().isEmpty());
        assertTrue(call.getVersions().isEmpty());

        call = new Call("test");
        assertEquals("test", call.getCall());
    }

    @Test
    public void createCallWithDataTypes(){
        call.withDataTypeJson().withDataTypeCsv();
        assertEquals(Arrays.asList(Call.DATATYPE_JSON, Call.DATATYPE_CSV), call.getDataTypes());
    }

    @Test
    public void createCallWithMethods(){
        call.withMethodGet().withMethodPost().withMethodPut().withMethodDelete();
        assertEquals(Arrays.asList(Call.METHOD_GET, Call.METHOD_POST, Call.METHOD_PUT, Call.METHOD_DELETE), call.getMethods());
    }

    @Test
    public void createCallWithVersions(){
        call.withVersionOneZero().withVersionOneOne().withVersionOneTwo().withVersionOneThree();
        assertEquals(Arrays.asList(Call.VERSION_ONE_ZERO, Call.VERSION_ONE_ONE, Call.VERSION_ONE_TWO, Call.VERSION_ONE_THREE), call.getVersions());
    }
}
