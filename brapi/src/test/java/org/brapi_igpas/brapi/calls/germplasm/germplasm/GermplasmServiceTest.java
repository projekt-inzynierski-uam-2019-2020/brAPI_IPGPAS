package org.brapi_igpas.brapi.calls.germplasm.germplasm;

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
public class GermplasmServiceTest {
    @Mock
    private GermplasmDao germplasmDao;

    @InjectMocks
    private GermplasmService germplasmService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsInResultData() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "LoremIpsum"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 2, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(germplasms, pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithGermplasmPUIEqualTestPUIInResultData() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(germplasms.get(1)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse("TestPUI",null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithGermplasmDbIdEqual2InResultData() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(germplasms.get(1)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null, "2", null, null,0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmWithGermplasmNameEqualName001InResultData() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(germplasms.get(0)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, "Name001", null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmWithCommonCropNameEqualTomatilloInResultData() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 1, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.singletonList(germplasms.get(0)), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, null, "Tomatillo", 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithEmptyListInResultDataWhenGermplasmPUIIsNotPresent() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse("TestPUI2",null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithEmptyListInResultDataWhenGermplasmDbIdIsNotPresent() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,"13", null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithEmptyListInResultDataWhenGermplasmNameIsNotPresent() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, "Name004", null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithGermplasmsWithEmptyListInResultDataWhenCommonCropNameIsNotPresent() {
        List<Germplasm> germplasms = Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "TestPUI"));

        when(germplasmDao.getAll()).thenReturn(germplasms);

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, null, "testCrop", 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailResponseWithEmptyDataInResult() {
        when(germplasmDao.getAll()).thenReturn(Collections.emptyList());

        Pagination pagination = new Pagination(0, 1000, 0, 1);
        BrApiDetailResponse expectedBrApiDetailResponse = new BrApiDetailResponse(Collections.emptyList(), pagination);

        BrApiDetailResponse actualBrApiDetailResponse = germplasmService.getBrApiDetailResponse(null,null, null, null, 0, 1000);

        assertEquals(expectedBrApiDetailResponse, actualBrApiDetailResponse);
    }
}
