package org.brapi_igpas.brapi.calls.calls;

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
@WebMvcTest(CallController.class)
public class CallControllerTest {
    // parameters
    private String dataType = null;
    private int page = 0;
    private int pageSize = 1000;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallService callService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailPayloadResponseWithCallsFromService() throws Exception {
        final String expectedResultData = "\"data\":[" +
                "{\"call\":\"calls\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]}," +
                "{\"call\":\"commoncropnames\",\"dataTypes\":[\"application/json\"],\"methods\":[\"GET\"],\"versions\":[\"1.3\"]}]";

        List<Call> calls = new ArrayList<>(Arrays.asList(
                new Call("calls")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree(),
                new Call("commoncropnames")
                        .withDataTypeJson()
                        .withMethodGet()
                        .withVersionOneThree()));

        when(callService.getBrApiDetailResponse(dataType, page, pageSize))
                .thenReturn(new BrApiDetailResponse(calls, PaginationUtils.getPaginationInfo(calls.size(), page, pageSize)));

        this.mockMvc.perform(get("/brapi/v1/calls"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(expectedResultData)));

        verify(callService, times(1)).getBrApiDetailResponse(dataType, page, pageSize);
        verifyNoMoreInteractions(callService);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/calls").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/calls").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/calls").param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/calls").param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/calls").param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }
}
