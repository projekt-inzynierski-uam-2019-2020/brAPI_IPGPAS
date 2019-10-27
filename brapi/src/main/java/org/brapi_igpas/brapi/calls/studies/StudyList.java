package org.brapi_igpas.brapi.calls.studies;

import org.brapi_igpas.brapi.calls.ListWrapper;

import java.util.*;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.utils.NumberParserUtils.safeParseLong;
import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

class StudyList extends ListWrapper<Study> {

    StudyList(List<Study> list) {
        super(list);
    }

    void filterByCommonCropName(String commonCropName) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByStudyTypeDbId(String studyTypeDbId) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getStudyTypeDbId(), studyTypeDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByProgramDbId(String programDbId) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getProgramDbId(), programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByLocationDbId(String locationDbId) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getLocationDbId(), locationDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterBySeasonDbId(String seasonDbId) {
        list = list.stream().filter(study -> study.getSeasons().stream()
                .anyMatch(season -> areParametersNonNullAndEquals(season.getSeasonDbId(), seasonDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByTrialDbId(String trialDbId) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getTrialDbId(), trialDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByStudyDbId(String studyDbId) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getStudyDbId(), studyDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByActive(String active) {
        list = list.stream().filter(study -> areParametersNonNullAndEquals(study.getActive(), active))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void sortBy(String sortBy) {
        switch (sortBy) {
            case "studyDbId":
                list.sort(Comparator.comparing(
                        study -> safeParseLong(study.getStudyDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "trialDbId":
                list.sort(Comparator.comparing(
                        study -> safeParseLong(study.getTrialDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "locationDbId":
                list.sort(Comparator.comparing(
                        study -> safeParseLong(study.getLocationDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programDbId":
                list.sort(Comparator.comparing(
                        study -> safeParseLong(study.getProgramDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "studyTypeDbId":
                list.sort(Comparator.comparing(
                        study -> safeParseLong(study.getStudyTypeDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "studyName":
                list.sort(Comparator.comparing(Study::getStudyName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "seasonDbId":
                //TODO
                break;
            case "locationName":
                list.sort(Comparator.comparing(Study::getLocationName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programName":
                list.sort(Comparator.comparing(Study::getProgramName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
        }
    }

    void sortOrder(String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(list);
        }
    }

}
