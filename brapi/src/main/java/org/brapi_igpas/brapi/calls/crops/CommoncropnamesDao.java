package org.brapi_igpas.brapi.calls.crops;

import java.util.List;

public interface CommoncropnamesDao {
    String getCommonCropNameWithStudyId(long studyId);

    List<String> getAll();
}
