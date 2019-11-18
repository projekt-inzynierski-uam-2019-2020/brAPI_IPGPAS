package org.brapi_igpas.brapi.calls.trials;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.NumberParserUtils.safeParseLong;
import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class TrialFilter {

    List<Trial> filterByCommonCropName(List<Trial> trials, String commonCropName) {
        return trials.stream().filter(trial -> areParametersNonNullAndEquals(trial.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Trial> filterByProgramDbId(List<Trial> trials, String programDbId) {
        return trials.stream().filter(trial -> areParametersNonNullAndEquals(trial.getProgramDbId(), programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Trial> filterByLocationDbId(List<Trial> trials, String locationDbId) {
        return trials.stream().filter(trial -> trial.getStudies().stream()
                .anyMatch(trialStudy -> areParametersNonNullAndEquals(trialStudy.getLocationDbId(), locationDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Trial> filterByActive(List<Trial> trials, String active) {
        return trials.stream().filter(trial -> areParametersNonNullAndEquals(Boolean.toString(trial.isActive()), active))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
