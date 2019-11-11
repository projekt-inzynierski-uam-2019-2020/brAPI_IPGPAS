package org.brapi_igpas.brapi.calls.germplasm;

import org.brapi_igpas.brapi.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

class GermplasmList extends ListWrapper<Germplasm> {

    GermplasmList(List<Germplasm> list) {
        super(list);
    }

    void filterByGermplasmPUI(String germplasmPUI) {
        list = list.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmPUI(), germplasmPUI))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByGermplasmDbId(String germplasmDbId) {
        list = list.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmDbId(), germplasmDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByGermplasmName(String germplasmName) {
        list = list.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmName(), germplasmName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void filterByCommonCropName(String commonCropName) {
        list = list.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
