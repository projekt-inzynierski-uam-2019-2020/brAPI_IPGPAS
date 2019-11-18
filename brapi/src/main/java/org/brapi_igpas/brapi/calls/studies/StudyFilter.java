package org.brapi_igpas.brapi.calls.studies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.NumberParserUtils.safeParseLong;
import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class StudyFilter {

    List<Study> filterByCommonCropName(List<Study> studies, String commonCropName) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByStudyTypeDbId(List<Study> studies, String studyTypeDbId) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getStudyTypeDbId(), studyTypeDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByProgramDbId(List<Study> studies, String programDbId) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getProgramDbId(), programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByLocationDbId(List<Study> studies, String locationDbId) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getLocationDbId(), locationDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterBySeasonDbId(List<Study> studies, String seasonDbId) {
        return studies.stream().filter(study -> study.getSeasons().stream()
                .anyMatch(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByTrialDbId(List<Study> studies, String trialDbId) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getTrialDbId(), trialDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByStudyDbId(List<Study> studies, String studyDbId) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getStudyDbId(), studyDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Study> filterByActive(List<Study> studies, String active) {
        return studies.stream().filter(study -> areParametersNonNullAndEquals(study.getActive(), active))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
