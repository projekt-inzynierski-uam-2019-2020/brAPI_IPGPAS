package org.brapi_igpas.brapi.calls.seasons;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.calls.study.seasons.Season;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonController;
import org.brapi_igpas.brapi.calls.study.seasons.SeasonService;
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
@WebMvcTest(SeasonController.class)
public class SeasonControllerTest {
    // parameters
    private String seasonDbId = null;
    private String season = null;
    private String year = null;
    private int page = 0;
    private int pageSize = 1000;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonService seasonService;

    @Test
    public void getBrApiDetailResponseShouldReturnBrApiDetailPayloadResponseWithSeasonsFromService() throws Exception {
        final String expectedResultData = "\"data\":[{\"season\":\"Summer\",\"seasonDbId\":\"1\",\"year\":\"2012\"}" +
                ",{\"season\":\"Winter\",\"seasonDbId\":\"2\",\"year\":\"2013\"}]";

        List<Season> seasons = new ArrayList<>(Arrays.asList(new Season("Summer", "1", "2012"),
                new Season("Winter", "2", "2013")));

        when(seasonService.getBrApiDetailResponse(seasonDbId, season, year, page, pageSize))
                .thenReturn(new BrApiDetailResponse(seasons, PaginationUtils.getPaginationInfo(seasons.size(), page, pageSize)));

        this.mockMvc.perform(get("/brapi/v1/seasons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(expectedResultData)));

        verify(seasonService, times(1)).getBrApiDetailResponse(seasonDbId, season, year, page, pageSize);
        verifyNoMoreInteractions(seasonService);
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/seasons").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/seasons").param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid query parameter\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/seasons").param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/seasons").param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }

    @Test
    public void getBrApiDetailResponseShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get("/brapi/v1/seasons").param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid\"")));
    }
}
