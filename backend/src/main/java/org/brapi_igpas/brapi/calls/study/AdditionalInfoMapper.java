package org.brapi_igpas.brapi.calls.study;

import org.brapi_igpas.brapi.calls.study.studies.Study;

import java.util.List;

public interface AdditionalInfoMapper {
    void setAdditionalInfoForStudies(List<Study> studies); // plural info
}
