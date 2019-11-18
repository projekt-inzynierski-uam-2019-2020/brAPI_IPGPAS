package org.brapi_igpas.brapi.calls.commoncropnames;

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
public class CommoncropnamesServiceImplTest {

    // parameters
    private int page;
    private int pageSize;

    @Mock
    private CommoncropnamesDAO commoncropnamesDAO;
    @Mock
    private PaginationService paginationService;

    @InjectMocks
    private CommoncropnamesServiceImpl commoncropnamesService;

    @Before
    public void init() {
        page = 0;
        pageSize = 1000;
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndDAOCommoncropnames() {
        List<String > DAOCommoncropnames = createNCommoncropnames(10);
        when(commoncropnamesDAO.getAll()).thenReturn(DAOCommoncropnames);

        Pagination DAOCommoncropnamesPagination = new Pagination(1, 1000, 10, 1);
        when(paginationService.getPagination(DAOCommoncropnames.size(), page, pageSize)).thenReturn(DAOCommoncropnamesPagination);
        when(paginationService.paginateList(DAOCommoncropnames, page, pageSize)).thenReturn(DAOCommoncropnames);

        BrAPIDetailResponse actualBrAPIResponse = commoncropnamesService.getBrAPIDetailResponse( page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(DAOCommoncropnamesPagination, DAOCommoncropnames);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    private List<String> createNCommoncropnames(int n) {
        return Collections.nCopies(n, "commoncrop");
    }
}
