package org.planth_pheno_analytics.brapi.api.germplasm;

import javax.validation.constraints.Size;

public class GermplasmFilterCriteria {

    @Size(max = 150, message = "germplasmPUI value is too long")
    private final String germplasmPUI;
    @Size(max = 150, message = "germplasmDbId value is too long")
    private final String germplasmDbId;
    @Size(max = 150, message = "germplasmName value is too long")
    private final String germplasmName;
    @Size(max = 150, message = "commoncropname value is too long")
    private final String commonCropName;

    public GermplasmFilterCriteria(String germplasmPUI, String germplasmDbId, String germplasmName, String commonCropName) {
        this.germplasmPUI = germplasmPUI;
        this.germplasmDbId = germplasmDbId;
        this.germplasmName = germplasmName;
        this.commonCropName = commonCropName;
    }

    public String getGermplasmPUI() {
        return germplasmPUI;
    }

    public String getGermplasmDbId() {
        return germplasmDbId;
    }

    public String getGermplasmName() {
        return germplasmName;
    }

    public String getCommonCropName() {
        return commonCropName;
    }
}

