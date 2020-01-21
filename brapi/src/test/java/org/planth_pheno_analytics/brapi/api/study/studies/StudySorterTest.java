package org.planth_pheno_analytics.brapi.api.study.studies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StudySorterTest {

    private StudySorter studySorter;

    @Before
    public void init() {
        studySorter = new StudySorter();
    }

    @Test
    public void sortByShouldSortStudiesByStudyDbId() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "studyDbId");

        //then
        Assert.assertNull(sorted.get(0).getStudyDbId());
        Assert.assertEquals("1", sorted.get(1).getStudyDbId());
        Assert.assertEquals("2", sorted.get(2).getStudyDbId());
        Assert.assertEquals("5", sorted.get(3).getStudyDbId());
    }

    @Test
    public void sortByShouldSortStudiesByTrialDbId() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "trialDbId");

        //then
        Assert.assertNull(sorted.get(0).getTrialDbId());
        Assert.assertEquals("1", sorted.get(1).getTrialDbId());
        Assert.assertEquals("2", sorted.get(2).getTrialDbId());
        Assert.assertEquals("3", sorted.get(3).getTrialDbId());
    }

    @Test
    public void sortByShouldSortStudiesByLocationDbId() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "locationDbId");

        //then
        Assert.assertEquals("1", sorted.get(0).getLocationDbId());
        Assert.assertEquals("2", sorted.get(1).getLocationDbId());
        Assert.assertEquals("4", sorted.get(2).getLocationDbId());
        Assert.assertEquals("6", sorted.get(3).getLocationDbId());
    }

    @Test
    public void sortByShouldSortStudiesByProgramDbId() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "programDbId");

        //then
        Assert.assertEquals("studyType0", sorted.get(0).getProgramDbId());
        Assert.assertEquals("studyType1", sorted.get(1).getProgramDbId());
        Assert.assertEquals("studyType2", sorted.get(2).getProgramDbId());
        Assert.assertEquals("studyType3", sorted.get(3).getProgramDbId());
    }

    @Test
    public void sortByShouldSortStudiesByStudyTypeDbId() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "studyTypeDbId");

        //then
        Assert.assertEquals("1", sorted.get(0).getStudyTypeDbId());
        Assert.assertEquals("2", sorted.get(1).getStudyTypeDbId());
        Assert.assertEquals("4", sorted.get(2).getStudyTypeDbId());
        Assert.assertEquals("5", sorted.get(3).getStudyTypeDbId());
    }


    @Test
    public void sortByShouldSortStudiesByLocationName() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "locationName");

        //then
        Assert.assertEquals("LocationA", sorted.get(0).getLocationName());
        Assert.assertEquals("LocationB", sorted.get(1).getLocationName());
        Assert.assertEquals("LocationC", sorted.get(2).getLocationName());
        Assert.assertEquals("LocationD", sorted.get(3).getLocationName());
    }

    @Test
    public void sortByShouldSortTrialByProgramName() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "programName");

        //then
        Assert.assertEquals("Program1", sorted.get(0).getProgramName());
        Assert.assertEquals("Program2", sorted.get(1).getProgramName());
        Assert.assertEquals("Program3", sorted.get(2).getProgramName());
        Assert.assertNull(sorted.get(3).getProgramName());
    }

    @Test
    public void sortByShouldReturnGivenListWhenSortByIsNotRecognized() {
        // given
        List<Study> studies = createDataList();

        // when
        List<Study> sorted = studySorter.sortBy(studies, "test");

        //then
        Assert.assertEquals(studies, sorted);
    }

    @Test
    public void sortOrderShouldChangeOrderToDESC() {
        // given
        List<Study> studies = createListSortedByStudyDbId();

        // when
        List<Study> sorted = studySorter.sortOrder(studies, "DESC");

        //then
        Assert.assertEquals("4", sorted.get(0).getStudyDbId());
        Assert.assertEquals("3", sorted.get(1).getStudyDbId());
        Assert.assertEquals("2", sorted.get(2).getStudyDbId());
        Assert.assertEquals("1", sorted.get(3).getStudyDbId());
    }


    private List<Study> createDataList() {
        return Arrays.asList(
                createStudy("2", "3", "2","studyType0","1","studyNameA", "LocationB", null),
                createStudy("1", "2", "4","studyType1","2","studyNameB","LocationC", "Program1"),
                createStudy("5", null, "1","studyType2","5","studyNameD","LocationD", "Program2"),
                createStudy(null, "1", "6","studyType3","4","studyNameC","LocationA","Program3"));
    }

    private List<Study> createListSortedByStudyDbId() {
        return Arrays.asList(
                createStudy("1", "3", "2","studyType0","1","studyNameA", "LocationB", null),
                createStudy("2", "2", "4","studyType1","2","studyNameB","LocationC", "Program1"),
                createStudy("3", null, "1","studyType2","5","studyNameD","LocationD", "Program2"),
                createStudy("4", "1", "6","studyType3","4","studyNameC","LocationA","Program3"));
    }


    // tests data set
    private Study createStudy(String studyDbId, String trialDbId, String locationDbId, String programDbId,
                              String studyTypeDbId, String studyName, String locationName, String programName) {
        Study study = new Study();
        study.setStudyDbId(studyDbId);
        study.setTrialDbId(trialDbId);
        study.setLocationDbId(locationDbId);
        study.setProgramDbId(programDbId);
        study.setProgramName(programName);
        study.setStudyTypeDbId(studyTypeDbId);
        study.setStudyName(studyName);
        study.setLocationName(locationName);
        return study;
    }
}
