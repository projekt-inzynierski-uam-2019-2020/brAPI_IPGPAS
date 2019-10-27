package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.igpas.entity.ValuesEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GermplasmMapper {

    List<Germplasm> mapValuesEntitiesToGermplasms(List<ValuesEntity> valuesEntities) {
        List<Germplasm> germplasms = new ArrayList<>();
        for (ValuesEntity valuesEntity : valuesEntities) {
            Germplasm germplasm = mapValuesEntityToGermplasm(valuesEntity);
            germplasms.add(germplasm);
        }
        return germplasms;
    }

    Germplasm mapValuesEntityToGermplasm(ValuesEntity valuesEntity) {
        Germplasm germplasm = new Germplasm();
        Optional<String> value = Optional.ofNullable(valuesEntity.getValue());
        value.ifPresent(germplasm::setGermplasmDbId);
        value.ifPresent(germplasm::setAccessionNumber);
        value.ifPresent(germplasm::setDefaultDisplayName);
        value.ifPresent(germplasm::setGermplasmName);
        return germplasm;
    }
}
