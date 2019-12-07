package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.planth_pheno_analytics.brapi.validator.ParameterValidator.areParametersNonNullAndEquals;

@Service
public class GermplasmFilter {

    Stream<Germplasm> filterByGermplasmPUI(Stream<Germplasm> germplasmStream, String germplasmPUI) {
        return germplasmStream.filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmPUI(), germplasmPUI));
    }

    Stream<Germplasm> filterByGermplasmDbId(Stream<Germplasm> germplasmStream, String germplasmDbId) {
        return germplasmStream.filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmDbId(), germplasmDbId));
    }

    Stream<Germplasm> filterByGermplasmName(Stream<Germplasm> germplasmStream, String germplasmName) {
        return germplasmStream.filter(germplasm -> areParametersNonNullAndEquals(germplasm.getGermplasmName(), germplasmName));
    }

    Stream<Germplasm> filterByCommonCropName(Stream<Germplasm> germplasmStream, String commonCropName) {
        return germplasmStream.filter(germplasm -> areParametersNonNullAndEquals(germplasm.getCommonCropName(), commonCropName));
    }
}
