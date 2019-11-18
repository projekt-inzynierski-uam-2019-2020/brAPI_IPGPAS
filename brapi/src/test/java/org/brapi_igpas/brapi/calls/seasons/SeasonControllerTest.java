package org.brapi_igpas.brapi.calls.seasons;

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
@WebMvcTest(SeasonController.class)
public class SeasonControllerTest {
    private final static String URL_TEMPLATE = "/brapi/v1/seasons";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonService seasonService;

    @Test
    public void getBrAPISeasonsShouldReturnBrAPIDetailResponseFromService() throws Exception {
        final String expectedResultData = "{\"metadata\":{\"datafiles\":[],\"pagination\":{\"currentPage\":1,\"pageSize\":1000,\"totalCount\":4,\"totalPages\":1},\"status\":[]},\"result\":{\"data\":[{\"season\":\"Summer\",\"seasonDbId\":\"1\",\"year\":\"Summer\"},{\"season\":\"Winter\",\"seasonDbId\":\"2\",\"year\":\"Winter\"},{\"season\":\"Fall\",\"seasonDbId\":\"3\",\"year\":\"Fall\"},{\"season\":\"2017\",\"seasonDbId\":\"4\",\"year\":\"2017\"}]}}";

        BrAPIDetailResponse response = createSeasonsBrAPIResponse();
        when(seasonService.getBrAPIDetailResponse( null, null, null, 0, 1000)).thenReturn(response);

        this.mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(expectedResultData));

        verify(seasonService, times(1)).getBrAPIDetailResponse( null, null, null, 0, 1000);
        verifyNoMoreInteractions(seasonService);
    }

    @Test
    public void getBrAPISeasonsShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
    }

    @Test
    public void getBrAPISeasonsShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPISeasonsShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Invalid parameter query\"")));
    }

    @Test
    public void getBrAPISeasonsShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPISeasonsShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    private BrAPIDetailResponse createSeasonsBrAPIResponse() {
        List<Season> seasons = Arrays.asList(
                createSeasonsWithValueAndId("Summer", "1"),
                createSeasonsWithValueAndId("Winter", "2"),
                createSeasonsWithValueAndId("Fall", "3"),
                createSeasonsWithValueAndId("2017", "4")
        );
        Pagination pagination = new Pagination(1, 1000, 4, 1);
        return new BrAPIDetailResponse(pagination, seasons);
    }

    private Season createSeasonsWithValueAndId(String value, String id) {
        Season season = new Season();
        season.setSeason(value);
        season.setYear(value);
        season.setSeasonDbId(id);
        return season;
    }
}
