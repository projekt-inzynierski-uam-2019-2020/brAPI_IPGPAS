package org.planth_pheno_analytics.brapi.api.trials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrialController.class)
public class TrialControllerTest {

    private final static String URL_TEMPLATE = "/brapi/v1/trials";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrialService trialService;

    @MockBean
    private ResponseCreator responseCreator;

    @Test
    public void getBrAPIStudiesShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'page'")));
    }

    @Test
    public void getBrAPIStudiesShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPIStudiesShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'pageSize'")));
    }

    @Test
    public void getBrAPIStudiesShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPIStudiesShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }
}
