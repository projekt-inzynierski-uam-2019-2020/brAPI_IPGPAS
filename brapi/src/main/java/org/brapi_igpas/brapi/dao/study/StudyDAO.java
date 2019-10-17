package org.brapi_igpas.brapi.dao.study;

import org.brapi_igpas.brapi.domain.calls.study.Study;

import java.util.List;

public interface StudyDAO {
    List<Study> getAll();
}
