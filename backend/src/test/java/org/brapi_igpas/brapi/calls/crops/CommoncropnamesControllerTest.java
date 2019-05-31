package org.brapi_igpas.brapi.calls.crops;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.PaginationUtils;
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
@WebMvcTest(CommoncropnamesController.class)
public class CommoncropnamesControllerTest {
    // parameters
    private int page = 0;
    private int pageSize = 1000;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommoncropnamesDao commoncropnamesDao;

    @Test
    public void getAllShouldReturnBrApiDetailPayloadResponseWithCommoncropnamesFromDao() throws Exception {
        final String expectedResultData = "\"data\":[\"Lorem\",\"Ipsum\"]}}";

        List<String> commoncropnames = new ArrayList<>(Arrays.asList("Lorem", "Ipsum"));

        when(commoncropnamesDao.getAll(page, pageSize))
                .thenReturn(new BrApiDetailResponse(commoncropnames, PaginationUtils.getPaginationInfo(commoncropnames.size(), page, pageSize)));

        this.mockMvc.perform(get("/brapi/v1/commoncropnames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(expectedResultData)));

        verify(commoncropnamesDao, times(1)).getAll(page, pageSize);
        verifyNoMoreInteractions(commoncropnamesDao);
    }

    @Test
    public void getAllShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/commoncropnames").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getAllShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/commoncropnames").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getAllShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/commoncropnames").param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid\"")));
    }

    @Test
    public void getAllShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/commoncropnames").param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }

    @Test
    public void getAllShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/commoncropnames").param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }
}
