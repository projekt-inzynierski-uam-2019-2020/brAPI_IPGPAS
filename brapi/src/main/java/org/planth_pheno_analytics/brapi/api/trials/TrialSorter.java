package org.planth_pheno_analytics.brapi.api.trials;

import org.planth_pheno_analytics.brapi.api.Sorter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static org.planth_pheno_analytics.brapi.utils.NumberParserUtils.safeParseLong;

@Service
public class TrialSorter extends Sorter<Trial> {

    @Override
    protected List<Trial> sortBy(List<Trial> trials, String sortBy) {
        switch (sortBy) {
            case "trialDbId":
                trials.sort(Comparator.comparing(
                        trial -> safeParseLong(trial.getTrialDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "programDbId":
                trials.sort(Comparator.comparing(
                        trial -> safeParseLong(trial.getProgramDbId()), Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "trialName":
                trials.sort(Comparator.comparing(Trial::getTrialName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "locationDbId":
                //TODO
                break;
            case "programName":
                trials.sort(Comparator.comparing(Trial::getProgramName, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "startDate":
                trials.sort(Comparator.comparing(Trial::getStartDate, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
            case "endDate":
                trials.sort(Comparator.comparing(Trial::getEndDate, Comparator.nullsLast(Comparator.naturalOrder())
                ));
                break;
        }
        return trials;
    }
}
