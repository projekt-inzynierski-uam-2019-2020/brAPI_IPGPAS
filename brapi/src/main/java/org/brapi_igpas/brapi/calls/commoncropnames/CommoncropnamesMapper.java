package org.brapi_igpas.brapi.calls.commoncropnames;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommoncropnamesMapper {

    List<String> mapValuesEntitiesToCommoncropnames(List<ValuesEntity> valuesEntities) {
        List<String> commoncropnames = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Optional<String> value = mapValuesEntityToCommoncropname(valuesEntity);
            value.ifPresent(commoncropnames::add);
        }
        return commoncropnames;
    }

    Optional<String> mapValuesEntityToCommoncropname(ValuesEntity valuesEntity) {
        return Optional.ofNullable(valuesEntity.getValue());
    }
}
