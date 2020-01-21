package org.planth_pheno_analytics.brapi.api.trials;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrialSorterTest {

    private TrialSorter trialSorter;

    @Before
    public void init() {
        trialSorter = new TrialSorter();
    }

    @Test
    public void sortByShouldSortTrialsByTrialDbId() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "trialDbId");

        //then
        Assert.assertNull(sorted.get(0).getTrialDbId());
        Assert.assertEquals("1", sorted.get(1).getTrialDbId());
        Assert.assertEquals("2", sorted.get(2).getTrialDbId());
        Assert.assertEquals("5", sorted.get(3).getTrialDbId());
    }

    @Test
    public void sortByShouldSortTrialsByProgramDbId() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "programDbId");

        //then
        Assert.assertNull(sorted.get(0).getProgramDbId());
        Assert.assertEquals("1", sorted.get(1).getProgramDbId());
        Assert.assertEquals("2", sorted.get(2).getProgramDbId());
        Assert.assertEquals("3", sorted.get(3).getProgramDbId());
    }

    @Test
    public void sortByShouldSortTrialsByTrialName() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "trialName");

        //then
        Assert.assertEquals("Trial1", sorted.get(0).getTrialName());
        Assert.assertEquals("Trial2", sorted.get(1).getTrialName());
        Assert.assertEquals("Trial3", sorted.get(2).getTrialName());
        Assert.assertNull(sorted.get(3).getTrialName());
    }

    @Test
    public void sortByShouldSortTrialsByProgramName() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "programName");

        //then
        Assert.assertEquals("Program1", sorted.get(0).getProgramName());
        Assert.assertEquals("Program2", sorted.get(1).getProgramName());
        Assert.assertEquals("Program3", sorted.get(2).getProgramName());
        Assert.assertNull(sorted.get(3).getProgramName());
    }

    @Test
    public void sortByShouldSortTrialsByStartDate() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "startDate");

        //then
        Assert.assertEquals("2011", sorted.get(0).getStartDate());
        Assert.assertEquals("2012", sorted.get(1).getStartDate());
        Assert.assertEquals("2012", sorted.get(2).getStartDate());
        Assert.assertEquals("2013", sorted.get(3).getStartDate());
    }

    @Test
    public void sortByShouldSortTrialsByEndDate() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "endDate");

        //then
        Assert.assertEquals("2013", sorted.get(0).getEndDate());
        Assert.assertEquals("2015", sorted.get(1).getEndDate());
        Assert.assertEquals("2015", sorted.get(2).getEndDate());
        Assert.assertEquals("2016", sorted.get(3).getEndDate());
    }

    @Test
    public void sortByShouldReturnGivenListWhenSortByIsNotRecognized() {
        // given
        List<Trial> trials = createDataList();

        // when
        List<Trial> sorted = trialSorter.sortBy(trials, "test");

        //then
        Assert.assertEquals(trials, sorted);
    }

    @Test
    public void sortOrderShouldChangeOrderToDESC() {
        // given
        List<Trial> trials = createSortedList();

        // when
        List<Trial> sorted = trialSorter.sortOrder(trials, "DESC");

        //then
        Assert.assertEquals("4", sorted.get(0).getTrialDbId());
        Assert.assertEquals("3", sorted.get(1).getTrialDbId());
        Assert.assertEquals("2", sorted.get(2).getTrialDbId());
        Assert.assertEquals("1", sorted.get(3).getTrialDbId());
    }


    private List<Trial> createDataList() {
        return Arrays.asList(
                createTrial("2", "3", "Trial1", null, "2012", "2015"),
                createTrial("1", "2", null, "Program1", "2013", "2016"),
                createTrial("5", null, "Trial2", "Program2", "2011", "2015"),
                createTrial(null, "1", "Trial3", "Program3", "2012", "2013"));
    }

    private List<Trial> createSortedList() {
        return Arrays.asList(
                createTrial("1", "1", "Trial1", "Program0", "2011", "2012"),
                createTrial("2", "2", "Trial2", "Program1", "2011", "2013"),
                createTrial("3", "3", "Trial3", "Program2", "2012", "2014"),
                createTrial("4", "4", "Trial4", "Program3", "2013", "2015"));
    }

    // tests data set
    private Trial createTrial(String trialDbId, String programDbId, String trialName, String programName, String startDate, String endDate) {
        Trial trial = new Trial();
        trial.setTrialDbId(trialDbId);
        trial.setProgramDbId(programDbId);
        trial.setTrialName(trialName);
        trial.setProgramName(programName);
        trial.setStartDate(startDate);
        trial.setEndDate(endDate);
        return trial;
    }
}
