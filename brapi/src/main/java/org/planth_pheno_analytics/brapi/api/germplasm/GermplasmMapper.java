package org.planth_pheno_analytics.brapi.api.germplasm;

import org.springframework.stereotype.Service;


@Service
public class GermplasmMapper {

    public Germplasm mapToGermplasm(GermplasmProjection germplasmProjection) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmDbId(germplasmProjection.getGermplasmDbId());
        germplasm.setAccessionNumber(germplasmProjection.getAccessionNumber());
        germplasm.setDefaultDisplayName(germplasmProjection.getDefaultDisplayName());
        germplasm.setGermplasmName(germplasmProjection.getGermplasmName());
        return germplasm;
    }
}
