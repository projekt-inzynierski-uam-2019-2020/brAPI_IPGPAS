package org.planth_pheno_analytics.brapi.api.germplasm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.planth_pheno_analytics.brapi.api.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GermplasmController.class)
public class GermplasmControllerTest {

    private final static String URL_TEMPLATE = "/brapi/v1/germplasm";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GermplasmService germplasmService;

    @MockBean
    private ResponseCreator responseCreator;

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidParameterFormatStatusWhenPageIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'page'")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageParameterStatusWhenPageIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("page", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'page' value is invalid.\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidParameterFormatStatusWhenPageSizeIsString() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "Lorem ipsum"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"Failed to convert property value of type 'java.lang.String' to required type 'int' for property 'pageSize'")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsZero() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }

    @Test
    public void getBrAPIGermplasmShouldReturnInvalidPageSizeParameterStatusWhenPageSizeIsNegative() throws Exception {
        this.mockMvc.perform(get(URL_TEMPLATE).param("pageSize", "-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("\"message\":\"'pageSize' value is invalid.\"")));
    }
}
