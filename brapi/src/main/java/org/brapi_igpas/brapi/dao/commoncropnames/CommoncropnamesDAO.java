package org.brapi_igpas.brapi.dao.commoncropnames;

import java.util.List;
import java.util.Optional;

public interface CommoncropnamesDAO {
    List<String> getAll();

    Optional<String> getCommoncropnameWithStudyId(long studyId);
}
