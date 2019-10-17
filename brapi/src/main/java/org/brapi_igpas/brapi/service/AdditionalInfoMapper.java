package org.brapi_igpas.brapi.service;

import org.brapi_igpas.brapi.domain.calls.study.Study;

import java.util.List;

public interface AdditionalInfoMapper {
    void mapAdditionalInfo(List<Study> studies); // plural info
}
