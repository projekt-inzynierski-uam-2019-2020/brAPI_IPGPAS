package org.brapi_igpas.brapi.domain.calls.study;

import org.brapi_igpas.brapi.domain.calls.ListWrapper;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

public class StudyList extends ListWrapper<Study> {

    public StudyList(List<Study> list) {
        super(list);
    }

    public void filterByCommonCropName(String commonCropName) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByStudyTypeDbId(String studyTypeDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getStudyTypeDbId(), studyTypeDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByProgramDbId(String programDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getProgramDbId(), programDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByLocationDbId(String locationDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getLocationDbId(), locationDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterBySeasonDbId(String seasonDbId) {
        list = list.stream().filter(study -> study.getSeasons().stream().anyMatch(season -> season.getSeasonDbId().equals(seasonDbId)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByTrialDbId(String trialDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getTrialDbId(), trialDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByStudyDbId(String studyDbId) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getStudyDbId(), studyDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByActive(String active) {
        list = list.stream().filter(s -> areParametersNonNullAndEquals(s.getActive(), active))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //TODO zaimplementowac w inny sposob
    public void sortBy(String sortBy) {
        switch (sortBy) {
            case "studyDbId":
                list.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "trialDbId":
                list.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getTrialDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "locationDbId":
                list.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getLocationDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "programDbId":
                list.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getProgramDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyTypeDbId":
                list.sort(nullsFirst(comparing(
                        s -> Long.parseLong(s.getStudyTypeDbId()), nullsFirst(naturalOrder())
                )));
                break;
            case "studyName":
                list.sort(nullsFirst(comparing(
                        Study::getStudyName, nullsFirst(naturalOrder())
                )));
                break;
            case "locationName":
                list.sort(nullsFirst(comparing(Study::getLocationName, nullsFirst(naturalOrder())
                )));
                break;
            case "programName":
                list.sort(nullsFirst(comparing(
                        Study::getProgramName, nullsFirst(naturalOrder())
                )));
                break;
        }
    }

    public void orderBySortOrder(String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(list);
        }
    }

}
