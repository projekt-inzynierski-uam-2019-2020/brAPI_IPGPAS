package org.planth_pheno_analytics.brapi.api.trials;

import java.util.Objects;

public class TrialStudy {
    private String locationDbId;
    private String locationName;
    private String studyDbId;
    private String studyName;

    TrialStudy() {
    }

    public String getLocationDbId() {
        return locationDbId;
    }

    public void setLocationDbId(String locationDbId) {
        this.locationDbId = locationDbId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStudyDbId() {
        return studyDbId;
    }

    public void setStudyDbId(String studyDbId) {
        this.studyDbId = studyDbId;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrialStudy that = (TrialStudy) o;
        return Objects.equals(locationDbId, that.locationDbId) &&
                Objects.equals(locationName, that.locationName) &&
                Objects.equals(studyDbId, that.studyDbId) &&
                Objects.equals(studyName, that.studyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationDbId, locationName, studyDbId, studyName);
    }
}
