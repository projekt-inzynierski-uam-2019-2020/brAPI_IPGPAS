package org.planth_pheno_analytics.brapi.api.calls;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CallBuilderTest {
    private Call call;

    @Test
    public void createEmpty() {
        // when
        call = new Call.Builder("call").build();

        // then
        Assert.assertEquals("call", call.getCall());
        Assert.assertTrue(call.getDataTypes().isEmpty());
        Assert.assertTrue(call.getMethods().isEmpty());
        Assert.assertTrue(call.getVersions().isEmpty());
    }

    @Test
    public void createCallWithDataTypes() {
        // when
        call = new Call.Builder("call")
                .withDataTypeJson()
                .withDataTypeCsv()
                .build();

        // then
        List<String> expectedDataTypes = Arrays.asList(
                Call.Builder.DATATYPE_JSON,
                Call.Builder.DATATYPE_CSV
        );
        Assert.assertEquals(expectedDataTypes, call.getDataTypes());
    }

    @Test
    public void createCallWithMethods() {
        // when
        call = new Call.Builder("call")
                .withMethodGet()
                .withMethodPost()
                .withMethodPut()
                .withMethodDelete()
                .build();

        // then
        List<String> expectedMethods = Arrays.asList(
                Call.Builder.METHOD_GET,
                Call.Builder.METHOD_POST,
                Call.Builder.METHOD_PUT,
                Call.Builder.METHOD_DELETE
        );
        Assert.assertEquals(expectedMethods, call.getMethods());
    }

    @Test
    public void createCallWithVersions() {
        // when
        call = new Call.Builder("call")
                .withVersionOneZero()
                .withVersionOneOne()
                .withVersionOneTwo()
                .withVersionOneThree()
                .build();

        // then
        List<String> expectedVersions = Arrays.asList(
                Call.Builder.VERSION_ONE_ZERO,
                Call.Builder.VERSION_ONE_ONE,
                Call.Builder.VERSION_ONE_TWO,
                Call.Builder.VERSION_ONE_THREE
        );
        Assert.assertEquals(expectedVersions, call.getVersions());
    }
}
