package org.brapi_igpas.brapi.calls.crops;

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
public class CommoncropnamesServiceTest {
    @Mock
    private CommoncropnamesDao commoncropnamesDao;

    @InjectMocks
    private CommoncropnamesService commoncropnamesService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithCommoncropnamesInResultData() {
        List<String> commoncropnames = Arrays.asList("Tomatillo", "Hordeum Vulgare");
        when(commoncropnamesDao.getAll()).thenReturn(commoncropnames);

        Pagination pagination = new Pagination(0, 1000, 2, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(commoncropnames, pagination);

        BrApiDetailResponse actualBrApiDetailResponse = commoncropnamesService.getBrApiDetailResponse(0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithEmptyDataInResult() {
        when(commoncropnamesDao.getAll()).thenReturn(Collections.emptyList());

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = commoncropnamesService.getBrApiDetailResponse(0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }
}
