package org.brapi_igpas.brapi.calls.studies;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.brapi_igpas.brapi.utils.NumberParserUtils.safeParseLong;

@Service
public class StudySorter {

    List<Study> sortBy(List<Study> studies, String sortBy) {
        switch (sortBy) {
            case "studyDbId":
                studies.sort(Comparator.comparing(
                        study -> safeParseLong(study.getStudyDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "trialDbId":
                studies.sort(Comparator.comparing(
                        study -> safeParseLong(study.getTrialDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "locationDbId":
                studies.sort(Comparator.comparing(
                        study -> safeParseLong(study.getLocationDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programDbId":
                studies.sort(Comparator.comparing(
                        study -> safeParseLong(study.getProgramDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "studyTypeDbId":
                studies.sort(Comparator.comparing(
                        study -> safeParseLong(study.getStudyTypeDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "studyName":
                studies.sort(Comparator.comparing(Study::getStudyName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "seasonDbId":
                //TODO
                break;
            case "locationName":
                studies.sort(Comparator.comparing(Study::getLocationName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programName":
                studies.sort(Comparator.comparing(Study::getProgramName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
        }
        return studies;
    }

    List<Study> sortOrder(List<Study> studies, String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(studies);
        }
        return studies;
    }
}
