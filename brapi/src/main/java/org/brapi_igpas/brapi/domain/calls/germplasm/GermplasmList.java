package org.brapi_igpas.brapi.domain.calls.germplasm;

import org.brapi_igpas.brapi.domain.calls.ListWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

public class GermplasmList extends ListWrapper<Germplasm> {

    public GermplasmList(List<Germplasm> list) {
        super(list);
    }

    public void filterByGermplasmPUI(String germplasmPUI) {
        list = list.stream().filter(g -> areParametersNonNullAndEquals(g.getGermplasmPUI(), germplasmPUI))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByGermplasmDbId(String germplasmDbId) {
        list = list.stream().filter(g -> areParametersNonNullAndEquals(g.getGermplasmDbId(), germplasmDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByGermplasmName(String germplasmName) {
        list = list.stream().filter(g -> areParametersNonNullAndEquals(g.getGermplasmName(), germplasmName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void filterByCommonCropName(String commonCropName) {
        list = list.stream().filter(g -> areParametersNonNullAndEquals(g.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
