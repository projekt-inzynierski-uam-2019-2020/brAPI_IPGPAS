package org.planth_pheno_analytics.brapi.api.trials;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.planth_pheno_analytics.brapi.api.study.studies.Study;
import org.planth_pheno_analytics.brapi.api.study.studies.StudyService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrialMapperTest {

    @Mock
    private StudyService studyService;

    @InjectMocks
    private TrialMapper trialMapper;

    @Test
    public void mapToTrialShouldMapValuesFromGivenTrialProjection() {
        // given
        TrialProjection trialProjection = mock(TrialProjection.class);
        when(trialProjection.getTrialDbId()).thenReturn("1");
        when(trialProjection.getTrialName()).thenReturn("Trial001");
        when(trialProjection.getTrialAdditionalInfoContactName()).thenReturn("Foo bar");
        when(trialProjection.getTrialAdditionalInfoDescription()).thenReturn("Description");
        when(trialProjection.getTrialAdditionalInfoSubmissionDate()).thenReturn("2012-03-01");

        when(studyService.getStudiesWithTrialDbId(1)).thenReturn(mockStudiesWithTrialDbId());

        // when
        Trial trial = trialMapper.mapToTrial(trialProjection);

        // then
        TrialAdditionalInfo trialAdditionalInfo = (TrialAdditionalInfo) trial.getAdditionalInfo();

        Assert.assertFalse(trial.isActive());
        Assert.assertEquals("1", trial.getTrialDbId());
        Assert.assertEquals("Trial001", trial.getTrialName());
        Assert.assertEquals("http://cropnet.pl/plantphenodb/index.php?id=1", trial.getDocumentationURL());
        Assert.assertEquals("Hordeum Vulgare", trial.getCommonCropName());
        Assert.assertEquals("1", trial.getStudies().get(0).getLocationDbId());
        Assert.assertEquals("Cerekwica", trial.getStudies().get(0).getLocationName());
        Assert.assertEquals("1", trial.getStudies().get(0).getStudyDbId());
        Assert.assertEquals("Study001", trial.getStudies().get(0).getStudyName());
        Assert.assertEquals("Description", trialAdditionalInfo.getDescription());
        Assert.assertEquals("Foo bar", trialAdditionalInfo.getFirstContactName());
        Assert.assertEquals("2012-03-01", trialAdditionalInfo.getSubmissionDate());
    }


    private List<Study> mockStudiesWithTrialDbId() {
        return Arrays.asList(
                createStudy("1", "Cerekwica", "1", "Study001"),
                createStudy("2", "Poznan", "3", "Study003")
        );
    }

    private Study createStudy(String locationDbId, String locationName, String studyDbId, String studyName) {
        Study study = new Study();
        study.setCommonCropName("Hordeum Vulgare");
        study.setLocationDbId(locationDbId);
        study.setLocationName(locationName);
        study.setStudyDbId(studyDbId);
        study.setStudyName(studyName);
        return study;
    }
}