package org.brapi_igpas.brapi.calls.trials;

import org.brapi_igpas.brapi.calls.ListWrapper;

import java.util.*;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;
import static org.brapi_igpas.brapi.utils.NumberParserUtils.safeParseLong;

class TrialList extends ListWrapper<Trial> {

    TrialList(List<Trial> list) {
        super(list);
    }

    void filterByCommonCropName(String commonCropName) {
        list = list.stream().filter(trial -> areParametersNonNullAndEquals(trial.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByProgramDbId(String programDbId) {
        list = list.stream().filter(trial -> areParametersNonNullAndEquals(trial.getProgramDbId(), programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByLocationDbId(String locationDbId) {
        list = list.stream().filter(trial -> trial.getStudies().stream()
                .anyMatch(trialStudy -> areParametersNonNullAndEquals(trialStudy.getLocationDbId(), locationDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByActive(String active) {
        list = list.stream().filter(trial -> areParametersNonNullAndEquals(Boolean.toString(trial.isActive()), active))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void sortBy(String sortBy) {
        switch (sortBy) {
            case "trialDbId":
                list.sort(Comparator.comparing(
                        trial -> safeParseLong(trial.getTrialDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programDbId":
                list.sort(Comparator.comparing(
                        trial -> safeParseLong(trial.getProgramDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "trialName":
                list.sort(Comparator.comparing(Trial::getTrialName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "locationDbId":
                //TODO
                break;
            case "programName":
                list.sort(Comparator.comparing(Trial::getProgramName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "startDate":
                list.sort(Comparator.comparing(Trial::getStartDate, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "endDate":
                list.sort(Comparator.comparing(Trial::getEndDate, Comparator.nullsLast(Comparator.naturalOrder())
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
