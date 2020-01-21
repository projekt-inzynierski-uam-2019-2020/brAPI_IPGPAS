package org.planth_pheno_analytics.brapi.api.calls;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CallDAOImplTest {

    private CallDAOImpl callDAO;

    @Before
    public void init() {
        callDAO = new CallDAOImpl();
    }

    @Test
    public void getCallsShouldReturnAllAddedCalls() {
        Assert.assertEquals(getAddedCalls(), callDAO.getCalls());
    }

    private List<Call> getAddedCalls() {
        return Arrays.asList(
                new Call.Builder("calls")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("commoncropnames")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("seasons")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("germplasm")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("studies")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("studies/{studyDbId}/germplasm")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("studies/{studyDbId}/table")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("trials")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build()
        );
    }
}
