package org.planth_pheno_analytics.brapi.api.calls;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CallServiceImplTest {

    @Mock
    private CallDAO callDAO;

    @InjectMocks
    private CallServiceImpl callService;

    @Test
    public void getFilteredCallsShouldReturnCallsWithGivenDataType() {
        // given
        String dataType = "application/json";
        when(callDAO.getCalls()).thenReturn(getCalls());

        // when
        List<Call> filteredCalls = callService.getFilteredCalls(dataType);

        // then
        Assert.assertNotNull(filteredCalls);
        Assert.assertEquals(2, filteredCalls.size());
    }

    @Test
    public void getFilteredCallsShouldReturnAllCallsWhenDataTypeIsNull() {
        // given
        when(callDAO.getCalls()).thenReturn(getCalls());

        // when
        List<Call> filteredCalls = callService.getFilteredCalls(null);

        // then
        Assert.assertNotNull(filteredCalls);
        Assert.assertEquals(4, filteredCalls.size());
    }

    @Test
    public void getFilteredCallsShouldReturnNoneCallsWhenDataTypeDoesNotExist() {
        // given
        String dataType = "json";
        when(callDAO.getCalls()).thenReturn(getCalls());

        // when
        List<Call> filteredCalls = callService.getFilteredCalls(dataType);

        // then
        Assert.assertNotNull(filteredCalls);
        Assert.assertEquals(0, filteredCalls.size());
    }

    private List<Call> getCalls() {
        return Arrays.asList(
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
                new Call.Builder("seasons")
                        .withDataTypeCsv()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build(),
                new Call.Builder("studies")
                        .withDataTypeCsv()
                        .withMethodGet()
                        .withVersionOneThree()
                        .build()
        );
    }
}
