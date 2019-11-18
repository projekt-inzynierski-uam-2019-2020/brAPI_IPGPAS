package org.brapi_igpas.brapi.calls.calls;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.brapi_igpas.brapi.utils.PaginationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallServiceImplTest {

    // parameters
    private int page;
    private int pageSize;
    private String dataType;

    @Mock
    private CallDAO callDAO;
    @Mock
    private CallFilter callFilter;
    @Mock
    private PaginationService paginationService;

    @InjectMocks
    private CallServiceImpl callService;

    @Before
    public void init() {
        page = 0;
        pageSize = 1000;
        dataType = "rightDataType";
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredCallsWhenDataTypeIsPresent() {
        List<Call> DAOCalls = createNCalls(10);
        when(callDAO.getAll()).thenReturn(DAOCalls);

        List<Call> filteredCalls = createNCalls(6);
        when(callFilter.filterByDataType(DAOCalls, dataType)).thenReturn(filteredCalls);

        Pagination filteredCallsPagination = new Pagination(1, 1000, 6, 1);
        when(paginationService.getPagination(filteredCalls.size(), page, pageSize)).thenReturn(filteredCallsPagination);
        when(paginationService.paginateList(filteredCalls, page, pageSize)).thenReturn(filteredCalls);

        BrAPIDetailResponse actualBrAPIResponse = callService.getBrAPIDetailResponse(dataType, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredCallsPagination, filteredCalls);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndDAOCallsWhenDataTypeIsNotPresent() {
        List<Call> DAOCalls = createNCalls(10);
        when(callDAO.getAll()).thenReturn(DAOCalls);

        Pagination DAOCallPagination = new Pagination(1, 1000, 10, 1);
        when(paginationService.getPagination(DAOCalls.size(), page, pageSize)).thenReturn(DAOCallPagination);
        when(paginationService.paginateList(DAOCalls, page, pageSize)).thenReturn(DAOCalls);

        BrAPIDetailResponse actualBrAPIResponse = callService.getBrAPIDetailResponse(null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(DAOCallPagination, DAOCalls);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    private List<Call> createNCalls(int n) {
        return Collections.nCopies(n, new Call.Builder("call").build());
    }
}
