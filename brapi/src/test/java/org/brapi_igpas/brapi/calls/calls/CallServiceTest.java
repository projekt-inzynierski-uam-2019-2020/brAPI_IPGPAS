package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallServiceTest {
    @Mock
    private CallDao callDao;

    @InjectMocks
    private CallService callService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithCallsInResultData() {
        List<Call> calls = Arrays.asList(new Call("Lorem").withMethodGet().withDataTypeJson(), new Call("Ipsum").withMethodGet().withDataTypeJson());
        when(callDao.getAll()).thenReturn(calls);

        Pagination pagination = new Pagination(0, 1000, 2, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(calls, pagination);

        BrApiDetailResponse actualBrApiDetailResponse = callService.getBrApiDetailResponse(null,0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithCallsWithDataTypeCsvInResultData() {
        List<Call> calls = Arrays.asList(new Call("Lorem").withMethodGet().withDataTypeCsv(), new Call("Ipsum").withMethodGet().withDataTypeJson());
        when(callDao.getAll()).thenReturn(calls);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(calls.get(0)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = callService.getBrApiDetailResponse("text/csv",0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }
}
