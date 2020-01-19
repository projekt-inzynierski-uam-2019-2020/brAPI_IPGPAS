package org.planth_pheno_analytics.brapi.api.trials;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class TrialFilter {

    Stream<Trial> filterByCommonCropName(Stream<Trial> trialStream, String commonCropName) {
        return trialStream.filter(trial ->
                areParametersNonNullAndEquals(trial.getCommonCropName(), commonCropName));
    }

    Stream<Trial> filterByProgramDbId(Stream<Trial> trialStream, String programDbId) {
        return trialStream.filter(trial ->
                areParametersNonNullAndEquals(trial.getProgramDbId(), programDbId));
    }

    Stream<Trial> filterByLocationDbId(Stream<Trial> trialStream, String locationDbId) {
        return trialStream.filter(trial -> trial.getStudies().stream()
                .anyMatch(trialStudy ->
                        areParametersNonNullAndEquals(trialStudy.getLocationDbId(), locationDbId)));
    }

    Stream<Trial> filterByActive(Stream<Trial> trialStream, String active) {
        return trialStream.filter(trial ->
                areParametersNonNullAndEquals(Boolean.toString(trial.isActive()), active.toLowerCase()));
    }
}
