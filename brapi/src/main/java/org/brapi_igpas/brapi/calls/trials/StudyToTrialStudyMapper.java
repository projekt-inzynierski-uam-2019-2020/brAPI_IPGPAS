package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.calls.studies.Study;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyToTrialStudyMapper {

    List<TrialStudy> mapToTrialStudies(List<Study> studies) {
        List<TrialStudy> trialStudies = new ArrayList<>();
        for (Study study : studies) {
            trialStudies.add(mapToTrialStudy(study));
        }
        return trialStudies;
    }

    TrialStudy mapToTrialStudy(Study study) {
        TrialStudy trialStudy = new TrialStudy();
        trialStudy.setLocationDbId(study.getLocationDbId());
        trialStudy.setLocationName(study.getLocationName());
        trialStudy.setStudyDbId(study.getStudyDbId());
        trialStudy.setStudyName(study.getStudyName());
        return trialStudy;
    }
}
