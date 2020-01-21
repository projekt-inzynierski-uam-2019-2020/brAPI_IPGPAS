package org.planth_pheno_analytics.brapi.api.calls;

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
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CallController.class)
public class CallControllerTest {

    private final static String URL_TEMPLATE = "/brapi/v1/calls";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallService callService;

    @MockBean
    private ResponseCreator responseCreator;

    @Test
    public void getBrAPICallsShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":0,\"pageSize\":1000,\"totalCount\":3,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[{\"call\":\"brapi\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]},{\"call\":\"commoncropnames\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]},{\"call\":\"studies\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]}]}}";

        List<Call> mockedData = mockFilteredCalls();
        when(callService.getFilteredCalls(null)).thenReturn(mockedData);
        BrAPIDetailResponse mockedResponse = new BrAPIDetailResponse(Pagination.of(0,1000,3,1), mockedData);
        when(responseCreator.createPaginatedBrAPIResponse(mockedData, 0, 1000)).thenReturn(mockedResponse);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResultData));

        verify(callService, times(1)).getFilteredCalls(null);
        verifyNoMoreInteractions(callService);
    }

    @Test
    public void getBrAPICallsShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'page'")));
    }

    @Test
    public void getBrAPICallsShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPICallsResponseShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'pageSize'")));
    }

    @Test
    public void getBrAPICallsShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPICallsShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    private List<Call> mockFilteredCalls() {
        return Arrays.asList(
                new Call.Builder("brapi")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("commoncropnames")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("studies")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build()
        );
    }
}
