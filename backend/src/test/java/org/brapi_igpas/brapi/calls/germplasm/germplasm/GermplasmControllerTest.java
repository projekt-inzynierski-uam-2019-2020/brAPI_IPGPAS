package org.brapi_igpas.brapi.calls.germplasm.germplasm;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.utils.PaginationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
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
    // parameters
    private String germplasmPUI = null;
    private String germplasmDbId = null;
    private String germplasmName = null;
    private String commonCropName = null;
    private int page = 0;
    private int pageSize = 1000;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GermplasmService germplasmService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailPayloadResponseWithGermplasmsFromService() throws Exception {
        final String expectedResultData = "\"data\":[{\"accessionNumber\":null,\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":\"Tomatillo\",\"countryOfOriginCode\":null,\"defaultDisplayName\":null,\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"1\",\"germplasmGenus\":null,\"germplasmName\":\"Name001\",\"germplasmPUI\":\"LoremIpsum\",\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null}," +
                "{\"accessionNumber\":null,\"acquisitionDate\":null,\"biologicalStatusOfAccessionCode\":null,\"breedingMethodDbId\":null,\"commonCropName\":\"Hordeum vulgare\",\"countryOfOriginCode\":null,\"defaultDisplayName\":null,\"documentationURL\":null,\"donors\":null,\"genus\":null,\"germplasmDbId\":\"2\",\"germplasmGenus\":null,\"germplasmName\":\"Name002\",\"germplasmPUI\":\"LoremIpsum\",\"germplasmSpecies\":null,\"instituteCode\":null,\"instituteName\":null,\"pedigree\":null,\"seedSource\":null,\"species\":null,\"speciesAuthority\":null,\"subtaxa\":null,\"subtaxaAuthority\":null,\"synonyms\":null,\"taxonIds\":[],\"typeOfGermplasmStorageCode\":null}]";

        List<Germplasm> germplasms = new ArrayList<>(Arrays.asList(new Germplasm("Tomatillo", "1", "Name001", "LoremIpsum"),
                new Germplasm("Hordeum vulgare", "2", "Name002", "LoremIpsum")));

        when(germplasmService.getBrApiDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize))
                .thenReturn(new BrApiDetailResponse(germplasms, PaginationUtils.getPaginationInfo(germplasms.size(), page, pageSize)));

        this.mockMvc.perform(get("/brapi/v1/germplasm"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(expectedResultData)));

        verify(germplasmService, times(1)).getBrApiDetailResponse(germplasmPUI, germplasmDbId, germplasmName, commonCropName, page, pageSize);
        verifyNoMoreInteractions(germplasmService);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/germplasm").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/germplasm").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/germplasm").param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/germplasm").param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/germplasm").param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }
}
