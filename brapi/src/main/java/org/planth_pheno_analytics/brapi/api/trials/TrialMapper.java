package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.api.study.studies.Study;
import org.planth_pheno_analytics.brapi.api.study.studies.StudyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrialMapper {
    private final StudyService studyService;

    public TrialMapper(StudyService studyService) {
        this.studyService = studyService;
    }

    Trial mapToTrial(TrialProjection trialProjection) {
        Trial trial = new Trial();
        trial.setActive(false);
        trial.setTrialDbId(trialProjection.getTrialDbId());
        trial.setDocumentationURL("http://cropnet.pl/plantphenodb/index.php?id=" + trial.getTrialDbId());
        trial.setTrialName(trialProjection.getTrialName());

        TrialAdditionalInfo trialAdditionalInfo = mapToTrialAdditionalInfo(trialProjection);
        trial.setAdditionalInfo(trialAdditionalInfo);

        List<Study> studiesWithTrialDbId = studyService.getStudiesWithTrialDbId(Integer.parseInt(trial.getTrialDbId()));
        trial.setStudies(studiesWithTrialDbId.stream()
                .map(this::mapToTrialStudy)
                .collect(Collectors.toList()));

        if (studiesWithTrialDbId.get(0) != null) {
            String commonCropName = studiesWithTrialDbId.get(0).getCommonCropName();
            trial.setCommonCropName(commonCropName);
        }

        return trial;
    }

    private TrialAdditionalInfo mapToTrialAdditionalInfo(TrialProjection trialProjection) {
        TrialAdditionalInfo trialAdditionalInfo = new TrialAdditionalInfo();
        trialAdditionalInfo.setDescription(trialProjection.getTrialAdditionalInfoDescription());
        trialAdditionalInfo.setFirstContactName(trialProjection.getTrialAdditionalInfoContactName());
        trialAdditionalInfo.setSubmissionDate(trialProjection.getTrialAdditionalInfoSubmissionDate());
        return trialAdditionalInfo;
    }

    private TrialStudy mapToTrialStudy(Study study) {
        TrialStudy trialStudy = new TrialStudy();
        trialStudy.setLocationDbId(study.getLocationDbId());
        trialStudy.setLocationName(study.getLocationName());
        trialStudy.setStudyDbId(study.getStudyDbId());
        trialStudy.setStudyName(study.getStudyName());
        return trialStudy;
    }
}
