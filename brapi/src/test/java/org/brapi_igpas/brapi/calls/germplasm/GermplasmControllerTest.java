package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(GermplasmController.class)
public class GermplasmControllerTest {
    private final static String URL_TEMPLATE = "/brapi/v1/germplasm";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GermplasmService germplasmService;

    @Test
    public void getBrAPIGermplasmShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":1,\"pageSize\":1000,\"totalCount\":4,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[{\"accessionNumber\":\"Germplasm7\",\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":null,\"countryOfOriginCode\":null,\"defaultDisplayName\":\"Germplasm7\",\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"Germplasm7\",\"germplasmGenus\":null,\"germplasmName\":\"Germplasm7\",\"germplasmPUI\":null,\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null},{\"accessionNumber\":\"Germplasm2\",\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":null,\"countryOfOriginCode\":null,\"defaultDisplayName\":\"Germplasm2\",\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"Germplasm2\",\"germplasmGenus\":null,\"germplasmName\":\"Germplasm2\",\"germplasmPUI\":null,\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null},{\"accessionNumber\":\"Germplasm1\",\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":null,\"countryOfOriginCode\":null,\"defaultDisplayName\":\"Germplasm1\",\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"Germplasm1\",\"germplasmGenus\":null,\"germplasmName\":\"Germplasm1\",\"germplasmPUI\":null,\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null},{\"accessionNumber\":\"Germplasm6\",\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":null,\"countryOfOriginCode\":null,\"defaultDisplayName\":\"Germplasm6\",\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"Germplasm6\",\"germplasmGenus\":null,\"germplasmName\":\"Germplasm6\",\"germplasmPUI\":null,\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null}]}}";

        BrAPIDetailResponse response = createGermplasmBrAPIResponse();
        when(germplasmService.getBrAPIDetailResponse(null, null, null, null, 0, 1000)).thenReturn(response);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(expectedResultData)));

        verify(germplasmService, times(1)).getBrAPIDetailResponse(null, null, null, null, 0, 1000);
        verifyNoMoreInteractions(germplasmService);
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    private BrAPIDetailResponse createGermplasmBrAPIResponse() {
        List<Germplasm> calls = Arrays.asList(
                createGermplasmWithValue("Germplasm7"),
                createGermplasmWithValue("Germplasm2"),
                createGermplasmWithValue("Germplasm1"),
                createGermplasmWithValue("Germplasm6")
        );
        Pagination pagination = new Pagination(1, 1000, 4, 1);
        return new BrAPIDetailResponse(pagination, calls);
    }

    private Germplasm createGermplasmWithValue(String value) {
        Germplasm germplasm = new Germplasm();
        germplasm.setCommonCropName(null);
        germplasm.setGermplasmDbId(value);
        germplasm.setAccessionNumber(value);
        germplasm.setDefaultDisplayName(value);
        germplasm.setGermplasmName(value);
        return germplasm;
    }
}
