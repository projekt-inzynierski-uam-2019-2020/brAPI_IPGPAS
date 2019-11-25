package org.brapi_igpas.brapi.calls.germplasm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brapi_igpas.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class GermplasmFilter {

    List<Germplasm> filterByGermplasmPUI(List<Germplasm> germplasms, String germplasmPUI) {
        return germplasms.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmPUI(), germplasmPUI))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Germplasm> filterByGermplasmDbId(List<Germplasm> germplasms, String germplasmDbId) {
        return germplasms.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmDbId(), germplasmDbId))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Germplasm> filterByGermplasmName(List<Germplasm> germplasms, String germplasmName) {
        return germplasms.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmName(), germplasmName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    List<Germplasm> filterByCommonCropName(List<Germplasm> germplasms, String commonCropName) {
        return germplasms.stream().filter(germplasm -> areParametersNonNullAndEquals(germplasm.getCommonCropName(), commonCropName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
