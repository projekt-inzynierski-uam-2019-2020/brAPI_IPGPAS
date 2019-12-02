package org.planth_pheno_analytics.brapi.api.study.studies;

import javax.validation.constraints.Size;

public class StudyFilterCriteria {

    @Size(max = 150, message = "commoncropname value is too long")
    private final String commonCropName;
    @Size(max = 150, message = "studyTypeDbId value is too long")
    private final String studyTypeDbId;
    @Size(max = 150, message = "programDbId value is too long")
    private final String programDbId;
    @Size(max = 150, message = "locationDbId value is too long")
    private final String locationDbId;
    @Size(max = 150, message = "seasonDbId value is too long")
    private final String seasonDbId;
    @Size(max = 150, message = "trialDbId value is too long")
    private final String trialDbId;
    @Size(max = 150, message = "studyDbId value is too long")
    private final String studyDbId;
    @Size(max = 5, message = "active value is too long")
    private final String active;

    public StudyFilterCriteria(String commonCropName, String studyTypeDbId, String programDbId, String locationDbId,
                               String seasonDbId, String trialDbId, String studyDbId, String active) {
        this.commonCropName = commonCropName;
        this.studyTypeDbId = studyTypeDbId;
        this.programDbId = programDbId;
        this.locationDbId = locationDbId;
        this.seasonDbId = seasonDbId;
        this.trialDbId = trialDbId;
        this.studyDbId = studyDbId;
        this.active = active;
    }

    public String getCommonCropName() {
        return commonCropName;
    }

    public String getStudyTypeDbId() {
        return studyTypeDbId;
    }

    public String getProgramDbId() {
        return programDbId;
    }

    public String getLocationDbId() {
        return locationDbId;
    }

    public String getSeasonDbId() {
        return seasonDbId;
    }

    public String getTrialDbId() {
        return trialDbId;
    }

    public String getStudyDbId() {
        return studyDbId;
    }

    public String getActive() {
        return active;
    }
}
