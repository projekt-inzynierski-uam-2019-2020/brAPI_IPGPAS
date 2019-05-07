package org.brapi_igpas.api.calls;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CallController.class)
public class CallControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CallDao callDao;

    private Call firstTestCall;
    private Call secondTestCall;

    @Before
    public void init(){
        firstTestCall = new Call("calls")
                .withDataTypeJson().withMethodGet().withVersionOneOne().withVersionOneTwo().withVersionOneThree();
        secondTestCall = new Call("commoncropnames")
                .withDataTypeJson().withMethodGet().withVersionOneTwo().withVersionOneThree();
    }

    @Test
    public void getAllShouldReturnAllCalls() throws Exception {
        when(callDao.getAll(null,0,1000)).thenReturn(Arrays.asList(firstTestCall,secondTestCall));
        this.mockMvc.perform(get("/brapi/v1/calls").contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].call",
                        is(firstTestCall.getCall())))
                .andExpect(jsonPath("$[0].dataTypes",
                        is(firstTestCall.getDataTypes())))
                .andExpect(jsonPath("$[0].methods",
                        is(firstTestCall.getMethods())))
                .andExpect(jsonPath("$[0].versions",
                        is(firstTestCall.getVersions())))
                .andExpect(jsonPath("$[1].call",
                        is(secondTestCall.getCall())))
                .andExpect(jsonPath("$[1].dataTypes",
                        is(secondTestCall.getDataTypes())))
                .andExpect(jsonPath("$[1].methods",
                        is(secondTestCall.getMethods())))
                .andExpect(jsonPath("$[1].versions",
                        is(secondTestCall.getVersions())))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllShouldReturnAllCallsWithGivenDataType() throws Exception {
        String dataType = Call.DATATYPE_CSV;
        Call callWithCsvDataType = new Call("germplasm").withDataTypeCsv();
        when(callDao.getAll(dataType,0,1000)).thenReturn(Arrays.asList(callWithCsvDataType));
        this.mockMvc.perform(get("/brapi/v1/calls").param("dataType",dataType).contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].call",
                        is(callWithCsvDataType.getCall())))
                .andExpect(jsonPath("$[0].dataTypes",
                        is(callWithCsvDataType.getDataTypes())))
                .andExpect(jsonPath("$[0].methods",
                        is(callWithCsvDataType.getMethods())))
                .andExpect(jsonPath("$[0].versions",
                        is(callWithCsvDataType.getVersions())))
                .andExpect(status().isOk());
    }
}
