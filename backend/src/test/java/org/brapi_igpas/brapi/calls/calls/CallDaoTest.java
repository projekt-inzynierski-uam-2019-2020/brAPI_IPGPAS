package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.result.Result;
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

        callDao = new CallDao();
        callDao.CALLS = calls;
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithAllCalls() {
        Result result = new Result(calls);
        assertEquals(result.getData(), callDao.getAll(dataType, page, pageSize).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCallsWithGivenDataType() {
        Call callWithCsvType = new Call("Lorem ipsum").withDataTypeCsv().withMethodDelete();
        calls.add(callWithCsvType);
        Result result = new Result(Collections.singletonList(callWithCsvType));
        assertEquals(result.getData(), callDao.getAll("text/csv", page, pageSize).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCallsFromGivenPage() {
        Result result = new Result(calls.subList(1, 2));
        assertEquals(result.getData(), callDao.getAll(dataType, 1, 1).getResult().getData());
    }

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithEmptyList() {
        Result result = new Result(Collections.emptyList());
        assertEquals(result.getData(), callDao.getAll(dataType, 5, 50).getResult().getData());
    }
}
