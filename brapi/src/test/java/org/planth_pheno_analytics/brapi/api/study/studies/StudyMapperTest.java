package org.planth_pheno_analytics.brapi.api.study.studies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.planth_pheno_analytics.brapi.api.study.seasons.Season;
import org.planth_pheno_analytics.brapi.api.study.seasons.SeasonService;
import org.planth_pheno_analytics.data.repository.ValueEntityRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StudyMapperTest {

    @MockBean
    private ValueEntityRepository valueEntityRepository;

    @MockBean
    private SeasonService seasonService;

    @InjectMocks
    private StudyMapper studyMapper;

    @Test
    public void mapToStudyShouldMapValuesFromGivenStudyProjection(){
        // given
        StudyProjection studyProjection = mock(StudyProjection.class);
        when(studyProjection.getStudyDbId()).thenReturn("1");
        when(studyProjection.getTrialDbId()).thenReturn("1");
        when(studyProjection.getStudyName()).thenReturn("Study1");
        when(studyProjection.getTrialName()).thenReturn("Trial1");
        when(studyProjection.getName()).thenReturn("Study1");
        when(studyProjection.getStudyAdditionalInfoDescription()).thenReturn("Lorem ipsum");

        int studyId = 1;
        List<Season> seasons = Collections.singletonList(new Season());
        when(seasonService.getSeasonsByStudyDbId(studyId)).thenReturn(seasons);

        when(valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Growth facility", studyId))
                .thenReturn(Optional.of("Study Type"));

        when(valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Geographic location", studyId))
                .thenReturn(Optional.of("Location"));

        when(valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Study start", studyId))
                .thenReturn(Optional.of("2012-02-01"));

        when(valueEntityRepository.getValueByAttributeDisplayedNameAndStudyId("Organism", studyId))
                .thenReturn(Optional.of("Tomatillo"));

        // when
        Study study = studyMapper.mapToStudy(studyProjection);

        // then
        Assert.assertEquals("1", study.getStudyDbId());
        Assert.assertEquals("1", study.getTrialDbId());
        Assert.assertEquals("Study1", study.getStudyName());
        Assert.assertEquals("Trial1", study.getTrialName());
        Assert.assertEquals("Study1", study.getName());
        Assert.assertEquals("Study Type", study.getStudyType());
        Assert.assertEquals("Location", study.getLocationName());
        Assert.assertEquals("2012-02-01", study.getStartDate());
        Assert.assertEquals("Tomatillo", study.getCommonCropName());

    }

}
