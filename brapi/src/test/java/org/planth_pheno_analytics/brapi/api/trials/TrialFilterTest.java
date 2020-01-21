package org.planth_pheno_analytics.brapi.api.trials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.stream.Stream;

public class TrialFilterTest {

    private TrialFilter trialFilter;

    @Before
    public void init() {
        trialFilter = new TrialFilter();
    }

    @Test
    public void filterByProgramDbIdShouldFilterAllTrialsWithGivenProgramDbIdFromGivenStream() {
        // given
        String programDbId = "1";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(2, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenProgramDbIdIsNotPresentInGivenStream() {
        // given
        String programDbId = "1111";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String programDbId = "1";
        Stream<Trial> dataStream = Stream.empty();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenProgramDbIdIsNull() {
        // given
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByProgramDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldFilterAllTrialsWithGivenActiveFromGivenStream() {
        // given
        String active = "false";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByActive(dataStream, active);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldReturnEmptyStreamWhenGivenActiveIsNotPresentInGivenStream() {
        // given
        String active = "UNKNOWN";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByActive(dataStream, active);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String active = "false";
        Stream<Trial> dataStream = Stream.empty();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByActive(dataStream, active);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldFilterAllTrialsWithGivenLocationDbIdFromGivenStream() {
        // given
        String locationDbId = "1";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenLocationDbIdIsNotPresentInGivenStream() {
        // given
        String locationDbId = "21";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String locationDbId = "1";
        Stream<Trial> dataStream = Stream.empty();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenSeasonDbIdIsNull() {
        // given
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByLocationDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldFilterAllTrialsWithGivenCommonCropNameFromGivenStream() {
        // given
        String commonCropName = "Hordeum Vulgare";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(5, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNotPresentInGivenStream() {
        // given
        String commonCropName = "Tomatillo";
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String commonCropName = "Hordeum Vulgare";
        Stream<Trial> dataStream = Stream.empty();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNull() {
        // given
        Stream<Trial> dataStream = createDataStream();

        // when
        Stream<Trial> filteredStream = trialFilter.filterByCommonCropName(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    private Stream<Trial> createDataStream() {
        return Stream.of(
                createTrial("1", false, "1", "Hordeum Vulgare"),
                createTrial("2", true, "2", "Rye"),
                createTrial("7", true, "2", "Rye"),
                createTrial("3", false, "1", "Hordeum Vulgare"),
                createTrial("5", false, "4", "Hordeum Vulgare"),
                createTrial("3", true, "2", "Hordeum Vulgare"),
                createTrial("11", true, "1", null),
                createTrial("1", true, "2", "Rye"),
                createTrial("2", true, "6", "Rye"),
                createTrial(null, true, "7", "Hordeum Vulgare"));
    }

    // tests data set
    private Trial createTrial(String programDbId, boolean active, String locationDbId, String commonCropName) {
        Trial trial = new Trial();
        trial.setProgramDbId(programDbId);
        trial.setActive(active);

        TrialStudy trialStudy = new TrialStudy();
        trialStudy.setLocationDbId(locationDbId);

        trial.setStudies(Collections.singletonList(trialStudy));
        trial.setCommonCropName(commonCropName);
        return trial;
    }
}
