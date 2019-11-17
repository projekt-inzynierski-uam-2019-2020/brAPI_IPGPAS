package org.brapi_igpas.brapi.calls.germplasm;

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
public class GermplasmServiceImplTest {

    // parameters
    private String germplasmPUI;
    private String germplasmDbId;
    private String germplasmName;
    private String commonCropName;
    private int page;
    private int pageSize;

    @Mock
    private GermplasmDAO germplasmDAO;
    @Mock
    private GermplasmFilter germplasmFilter;
    @Mock
    private PaginationService paginationService;

    @InjectMocks
    private GermplasmServiceImpl germplasmService;

    @Before
    public void init() {
        page = 0;
        pageSize = 1000;
        germplasmPUI = "germplasmPUI";
        germplasmDbId = "12345";
        germplasmName = "germplasmName";
        commonCropName = "Tomatillo";
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndDAOGermplasmsWhenNoneOfFilteredParametersIsPresent() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        Pagination DAOCallPagination = new Pagination(1, 1000, 10, 1);
        when(paginationService.getPagination(DAOGermplasms.size(), page, pageSize)).thenReturn(DAOCallPagination);
        when(paginationService.paginateList(DAOGermplasms, page, pageSize)).thenReturn(DAOGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(null, null, null, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(DAOCallPagination, DAOGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredGermplasmByAllPresentParameters() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        List<Germplasm> filteredGermplasms = createNGermplasms(6);
        when(germplasmFilter.filterByGermplasmPUI(DAOGermplasms, germplasmPUI)).thenReturn(filteredGermplasms);

        List<Germplasm> previousFiltered = filteredGermplasms;
        filteredGermplasms = createNGermplasms(4);
        when(germplasmFilter.filterByGermplasmDbId(previousFiltered, germplasmDbId)).thenReturn(filteredGermplasms);

        previousFiltered = filteredGermplasms;
        filteredGermplasms = createNGermplasms(3);
        when(germplasmFilter.filterByGermplasmName(previousFiltered, germplasmName)).thenReturn(filteredGermplasms);

        previousFiltered = filteredGermplasms;
        filteredGermplasms = createNGermplasms(1);
        when(germplasmFilter.filterByCommonCropName(previousFiltered, commonCropName)).thenReturn(filteredGermplasms);

        Pagination filteredGermplasmsPagination = new Pagination(1, 1000, 1, 1);
        when(paginationService.getPagination(filteredGermplasms.size(), page, pageSize)).thenReturn(filteredGermplasmsPagination);
        when(paginationService.paginateList(filteredGermplasms, page, pageSize)).thenReturn(filteredGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredGermplasmsPagination, filteredGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredGermplasmByGermplasmPUIWhenIsPresentAndOtherParametersAreNull() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        List<Germplasm> filteredGermplasms = createNGermplasms(6);
        when(germplasmFilter.filterByGermplasmPUI(DAOGermplasms, germplasmPUI)).thenReturn(filteredGermplasms);

        Pagination filteredGermplasmsPagination = new Pagination(1, 1000, 6, 1);
        when(paginationService.getPagination(filteredGermplasms.size(), page, pageSize)).thenReturn(filteredGermplasmsPagination);
        when(paginationService.paginateList(filteredGermplasms, page, pageSize)).thenReturn(filteredGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(germplasmPUI, null, null, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredGermplasmsPagination, filteredGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredGermplasmByGermplasmDbIdWhenIsPresentAndOtherParametersAreNull() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        List<Germplasm> filteredGermplasms = createNGermplasms(4);
        when(germplasmFilter.filterByGermplasmDbId(DAOGermplasms, germplasmDbId)).thenReturn(filteredGermplasms);

        Pagination filteredGermplasmsPagination = new Pagination(1, 1000, 4, 1);
        when(paginationService.getPagination(filteredGermplasms.size(), page, pageSize)).thenReturn(filteredGermplasmsPagination);
        when(paginationService.paginateList(filteredGermplasms, page, pageSize)).thenReturn(filteredGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(null, germplasmDbId, null, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredGermplasmsPagination, filteredGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredGermplasmByGermplasmNameWhenIsPresentAndOtherParametersAreNull() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        List<Germplasm> filteredGermplasms = createNGermplasms(7);
        when(germplasmFilter.filterByGermplasmName(DAOGermplasms, germplasmName)).thenReturn(filteredGermplasms);

        Pagination filteredGermplasmsPagination = new Pagination(1, 1000, 7, 1);
        when(paginationService.getPagination(filteredGermplasms.size(), page, pageSize)).thenReturn(filteredGermplasmsPagination);
        when(paginationService.paginateList(filteredGermplasms, page, pageSize)).thenReturn(filteredGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(null, null, germplasmName, null, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredGermplasmsPagination, filteredGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    @Test
    public void getBrAPIDetailResponseShouldGetBrAPIResponseWithPaginationAndFilteredGermplasmByCommonCropNameNameWhenIsPresentAndOtherParametersAreNull() {
        List<Germplasm> DAOGermplasms = createNGermplasms(10);
        when(germplasmDAO.getAll()).thenReturn(DAOGermplasms);

        List<Germplasm> filteredGermplasms = createNGermplasms(5);
        when(germplasmFilter.filterByCommonCropName(DAOGermplasms, commonCropName)).thenReturn(filteredGermplasms);

        Pagination filteredGermplasmsPagination = new Pagination(1, 1000, 5, 1);
        when(paginationService.getPagination(filteredGermplasms.size(), page, pageSize)).thenReturn(filteredGermplasmsPagination);
        when(paginationService.paginateList(filteredGermplasms, page, pageSize)).thenReturn(filteredGermplasms);

        BrAPIDetailResponse actualBrAPIResponse = germplasmService.getBrAPIDetailResponse(null, null, null, commonCropName, page, pageSize);
        BrAPIDetailResponse expectedBrAPIResponse = new BrAPIDetailResponse(filteredGermplasmsPagination, filteredGermplasms);

        assertNotNull(actualBrAPIResponse);
        assertEquals(expectedBrAPIResponse, actualBrAPIResponse);
    }

    private List<Germplasm> createNGermplasms(int n) {
        return Collections.nCopies(n, new Germplasm());
    }
}
