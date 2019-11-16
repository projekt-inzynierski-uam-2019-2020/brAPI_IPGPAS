package org.brapi_igpas.brapi.calls.commoncropnames;

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
@WebMvcTest(CommoncropnamesController.class)
public class CommoncropnamesControllerTest {
    private final static String URL_TEMPLATE = "/brapi/v1/commoncropnames";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommoncropnamesService commoncropnamesService;

    @Test
    public void getBrAPICommoncropnamesShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":1,\"pageSize\":1000,\"totalCount\":2,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[\"Tomatillo\",\"Hordeum Vulgare\"]}}";

        BrAPIDetailResponse response = createCommoncropnamesBrAPIResponse();
        when(commoncropnamesService.getBrAPIDetailResponse(0, 1000)).thenReturn(response);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResultData));

        verify(commoncropnamesService, times(1)).getBrAPIDetailResponse(0, 1000);
        verifyNoMoreInteractions(commoncropnamesService);
    }

    @Test
    public void getBrAPICommoncropnamesShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
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
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
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

    private BrAPIDetailResponse createCommoncropnamesBrAPIResponse() {
        List<String> commonropnames = Arrays.asList("Tomatillo", "Hordeum Vulgare");
        Pagination pagination = new Pagination(1, 1000, 2, 1);
        return new BrAPIDetailResponse(pagination, commonropnames);
    }
}
