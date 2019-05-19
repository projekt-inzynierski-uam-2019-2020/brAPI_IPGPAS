package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class CallDaoTest {
    // default parameters
    private String dataType = null;
    private int page = 0;
    private int pageSize = 1000;

    private CallDao callDao;
    private List<Call> calls;
    private Call callWithCsvType;

    @Before
    public void init() {
        calls = new ArrayList<>();

        calls.add(new Call("calls")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneZero()
                .withVersionOneOne()
                .withVersionOneTwo()
                .withVersionOneThree());

        calls.add(new Call("commoncropnames")
                .withDataTypeJson()
                .withMethodGet()
                .withVersionOneTwo()
                .withVersionOneThree());

        callWithCsvType = new Call("Third").withDataTypeCsv();
        calls.add(callWithCsvType);

        callDao = new CallDao(calls);
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithAllCalls() {
        assertEquals(new BrApiDetailPayloadResponse(calls, new Pagination(0,0,0,0)).getResult().getData(), callDao.getAll(dataType, page, pageSize).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCallsWithGivenDataType() {
        assertEquals(new BrApiDetailPayloadResponse(Collections.singletonList(callWithCsvType), new Pagination(0,0,0,0)).getResult().getData(), callDao.getAll("text/csv", page, pageSize).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCallsFromGivenPage() {
        assertEquals(new BrApiDetailPayloadResponse(calls.subList(1,2), new Pagination(0,0,0,0)).getResult().getData(), callDao.getAll(dataType, 1, 1).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithEmptyList() {
        assertEquals(new BrApiDetailPayloadResponse(Collections.emptyList(), new Pagination(0,0,0,0)).getResult().getData(), callDao.getAll(dataType, 5, 50).getResult().getData());
    }
}
