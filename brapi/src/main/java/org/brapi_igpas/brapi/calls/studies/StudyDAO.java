package org.brapi_igpas.brapi.calls.studies;

import java.util.List;

public interface StudyDAO {
    List<Study> getStudiesWithTrialDbId(String trialDbId);

    List<Study> getAll();
}
