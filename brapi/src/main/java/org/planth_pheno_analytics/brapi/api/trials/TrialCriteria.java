package org.planth_pheno_analytics.brapi.api.trials;

import javax.validation.constraints.Size;

public class TrialCriteria {

    @Size(max = 150, message = "commoncropname value is too long")
    private final String commonCropName;
    @Size(max = 150, message = "programDbId value is too long")
    private final String programDbId;
    @Size(max = 150, message = "locationDbId value is too long")
    private final String locationDbId;
    @Size(max = 5, message = "active value is too long")
    private final String active;

    public TrialCriteria(String commonCropName, String programDbId, String locationDbId, String active) {
        this.commonCropName = commonCropName;
        this.programDbId = programDbId;
        this.locationDbId = locationDbId;
        this.active = active;
    }

    public String getCommonCropName() {
        return commonCropName;
    }

    public String getProgramDbId() {
        return programDbId;
    }

    public String getLocationDbId() {
        return locationDbId;
    }

    public String getActive() {
        return active;
    }
}
