package org.planth_pheno_analytics.brapi.api.germplasm;

import org.planth_pheno_analytics.data.entity.ValueEntity;
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

    Germplasm mapToGermplasm(ValueEntity valueEntity) {
        Germplasm germplasm = new Germplasm();
        germplasm.setGermplasmDbId(valueEntity.getValue());
        germplasm.setAccessionNumber(valueEntity.getValue());
        germplasm.setDefaultDisplayName(valueEntity.getValue());
        germplasm.setGermplasmName(valueEntity.getValue());
        return germplasm;
    }
}
