package org.planth_pheno_analytics.brapi.api.study.studies;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class StudyFilter {

    Stream<Study> filterByCommonCropName(Stream<Study> studyStream, String commonCropName) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getCommonCropName(), commonCropName));
    }

    Stream<Study> filterByStudyTypeDbId(Stream<Study> studyStream, String studyTypeDbId) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getStudyTypeDbId(), studyTypeDbId));
    }

    Stream<Study> filterByProgramDbId(Stream<Study> studyStream, String programDbId) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getProgramDbId(), programDbId));
    }

    Stream<Study> filterByLocationDbId(Stream<Study> studyStream, String locationDbId) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getLocationDbId(), locationDbId));
    }

    Stream<Study> filterBySeasonDbId(Stream<Study> studyStream, String seasonDbId) {
        return studyStream.filter(study -> study.getSeasons().stream()
                .anyMatch(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId)));
    }

    Stream<Study> filterByTrialDbId(Stream<Study> studyStream, String trialDbId) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getTrialDbId(), trialDbId));
    }

    Stream<Study> filterByStudyDbId(Stream<Study> studyStream, String studyDbId) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getStudyDbId(), studyDbId));
    }

    Stream<Study> filterByActive(Stream<Study> studyStream, String active) {
        return studyStream.filter(study -> areParametersNonNullAndEquals(study.getActive(), active));
    }
}
