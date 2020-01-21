package org.planth_pheno_analytics.brapi.api.study.studies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.planth_pheno_analytics.brapi.api.study.seasons.Season;

import java.util.Collections;
import java.util.stream.Stream;

public class StudyFilterTest {

    private StudyFilter studyFilter;

    @Before
    public void init() {
        studyFilter = new StudyFilter();
    }

    @Test
    public void filterByCommonCropNameShouldFilterAllStudiesWithGivenCommonCropNameFromGivenStream() {
        // given
        String commonCropName = "Tomatillo";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(4, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNotPresentInGivenStream() {
        // given
        String commonCropName = "Arabidopsis thaliana";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String commonCropName = "Tomatillo";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByCommonCropName(dataStream, commonCropName);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByCommonCropNameShouldReturnEmptyStreamWhenGivenCommonCropNameIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByCommonCropName(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyTypeDbIdShouldFilterAllStudiesWithGivenStudyTypeDbIdFromGivenStream() {
        // given
        String studyTypeDbId = "1";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyTypeDbId(dataStream, studyTypeDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(5, filteredStream.count());
    }

    @Test
    public void filterByStudyTypeDbIdShouldReturnEmptyStreamWhenGivenStudyTypeDbIdIsNotPresentInGivenStream() {
        // given
        String studyTypeDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyTypeDbId(dataStream, studyTypeDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyTypeDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String studyTypeDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyTypeDbId(dataStream, studyTypeDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyTypeDbIdShouldReturnEmptyStreamWhenGivenStudyTypeDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyTypeDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldFilterAllStudiesWithGivenProgramDbIdFromGivenStream() {
        // given
        String programDbId = "1";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(4, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenProgramDbIdIsNotPresentInGivenStream() {
        // given
        String programDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String programDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByProgramDbId(dataStream, programDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByProgramDbIdShouldReturnEmptyStreamWhenGivenProgramDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByProgramDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldFilterAllStudiesWithGivenLocationDbIdFromGivenStream() {
        // given
        String locationDbId = "4";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenLocationDbIdIsNotPresentInGivenStream() {
        // given
        String locationDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String locationDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByLocationDbId(dataStream, locationDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByLocationDbIdShouldReturnEmptyStreamWhenGivenLocationDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByLocationDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldFilterAllStudiesWithGivenSeasonIdFromGivenStream() {
        // given
        String seasonDbId = "1";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(4, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenSeasonDbIdIsNotPresentInGivenStream() {
        // given
        String seasonDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String seasonDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterBySeasonDbId(dataStream, seasonDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterBySeasonDbIdShouldReturnEmptyStreamWhenGivenSeasonDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterBySeasonDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByTrialDbIdShouldFilterAllStudiesWithGivenTrialDbIdFromGivenStream() {
        // given
        String trialDbId = "2";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByTrialDbId(dataStream, trialDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(4, filteredStream.count());
    }

    @Test
    public void filterByTrialDbIdShouldReturnEmptyStreamWhenGivenTrialDbIdIsNotPresentInGivenStream() {
        // given
        String trialDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByTrialDbId(dataStream, trialDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByTrialDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String trialDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByTrialDbId(dataStream, trialDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByTrialDbIdShouldReturnEmptyStreamWhenGivenTrialDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByTrialDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyDbIdShouldFilterAllStudiesWithGivenStudyDbIdFromGivenStream() {
        // given
        String studyDbId = "2";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyDbId(dataStream, studyDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(3, filteredStream.count());
    }

    @Test
    public void filterByStudyDbIdShouldReturnEmptyStreamWhenGivenStudyDbIdIsNotPresentInGivenStream() {
        // given
        String studyDbId = "9";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyDbId(dataStream, studyDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyDbIdShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String studyDbId = "1";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyDbId(dataStream, studyDbId);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByStudyDbIdShouldReturnEmptyStreamWhenGivenStudyDbIdIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByStudyDbId(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldFilterAllStudiesWithGivenActiveFromGivenStream() {
        // given
        String active = "true";
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByActive(dataStream, active);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(5, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldReturnEmptyStreamWhenGivenStreamIsEmpty() {
        // given
        String active = "false";
        Stream<Study> dataStream = Stream.empty();

        // when
        Stream<Study> filteredStream = studyFilter.filterByActive(dataStream, active);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }

    @Test
    public void filterByActiveShouldReturnEmptyStreamWhenGivenActiveIsNull() {
        // given
        Stream<Study> dataStream = createDataStream();

        // when
        Stream<Study> filteredStream = studyFilter.filterByActive(dataStream, null);

        // then
        Assert.assertNotNull(filteredStream);
        Assert.assertEquals(0, filteredStream.count());
    }


    private Stream<Study> createDataStream() {
        return Stream.of(
                createStudy("Tomatillo", "1", "1", "5", "1", "2", null, "false"),
                createStudy("Hordeum Vulgare", "1", "2", "4", null, "1", "2", "false"),
                createStudy("Tomatillo", null, "1", "3", "1", "3", "5", "true"),
                createStudy("Hordeum Vulgare", "2", "3", "4", "5", null, "2", "true"),
                createStudy(null, "1", "2", "2", "6", "2", "3", "false"),
                createStudy("Rye", "5", "1", "3", "1", "5", "1", "true"),
                createStudy("Rye", "3", null, "2", "3", "2", "2", "false"),
                createStudy("Tomatillo", "1", "1", null, "1", "1", "4", "true"),
                createStudy("Hordeum Vulgare", "4", "2", "4", "8", "1", "7", "true"),
                createStudy("Tomatillo", "1", "3", "1", "2", "2", "3", null));
    }

    // tests data set
    private Study createStudy(String commonCropName, String studyTypeDbId, String programDbId, String locationDbId,
                              String seasonDbId, String trialDbId, String studyDbId, String active) {
        Study study = new Study();
        study.setCommonCropName(commonCropName);
        study.setStudyTypeDbId(studyTypeDbId);
        study.setProgramDbId(programDbId);
        study.setLocationDbId(locationDbId);

        Season season = new Season();
        season.setSeasonDbId(seasonDbId);
        study.setSeasons(Collections.singletonList(season));

        study.setTrialDbId(trialDbId);
        study.setStudyDbId(studyDbId);
        study.setActive(active);
        return study;
    }
}
