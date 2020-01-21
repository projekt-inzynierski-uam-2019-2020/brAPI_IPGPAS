package org.planth_pheno_analytics.brapi.api.commoncropnames;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.planth_pheno_analytics.brapi.api.response.BrAPIDetailResponse;
import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommoncropnamesController.class)
public class CommoncropnamesControllerTest {

    private final static String URL_TEMPLATE = "/brapi/v1/commoncropnames";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommoncropnamesProjectionResources commoncropnamesProjectionResources;

    @MockBean
    private ResponseCreator responseCreator;

    @Test
    public void getBrAPICommoncropnamesShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":0,\"pageSize\":1000,\"totalCount\":2,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[\"Tomatillo\",\"Hordeum Vulgare\"]}}";

        List<String> mockedCommonCropNames = mockCommonCropNames();
        when(commoncropnamesProjectionResources.getCommoncropnames()).thenReturn(mockedCommonCropNames);
        BrAPIDetailResponse mockedResponse = new BrAPIDetailResponse(Pagination.of(0, 1000, 2, 1), mockedCommonCropNames);
        when(responseCreator.createPaginatedBrAPIResponse(mockedCommonCropNames, 0, 1000)).thenReturn(mockedResponse);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResultData));
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'page'")));
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'pageSize'")));
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    private List<String> mockCommonCropNames() {
        return Arrays.asList(
                "Tomatillo",
                "Hordeum Vulgare");
    }

}