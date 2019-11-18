package org.brapi_igpas.brapi.calls.calls;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
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
    private CallServiceImpl callService;

    @Test
    public void getBrAPICallsShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":1,\"pageSize\":1000,\"totalCount\":3,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[{\"call\":\"calls\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]},{\"call\":\"commoncropnames\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]},{\"call\":\"studies\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]}]}}";

        BrAPIDetailResponse response = createCallsBrAPIResponse();
        when(callService.getBrAPIDetailResponse(null, 0, 1000)).thenReturn(response);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResultData));

        verify(callService, times(1)).getBrAPIDetailResponse(null, 0, 1000);
        verifyNoMoreInteractions(callService);
    }

    @Test
    public void getBrAPICallsShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
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
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
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

    private BrAPIDetailResponse createCallsBrAPIResponse() {
        List<Call> calls = Arrays.asList(
                new Call.Builder("calls")
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
        Pagination pagination = new Pagination(1, 1000, 3, 1);
        return new BrAPIDetailResponse(pagination, calls);
    }
}
