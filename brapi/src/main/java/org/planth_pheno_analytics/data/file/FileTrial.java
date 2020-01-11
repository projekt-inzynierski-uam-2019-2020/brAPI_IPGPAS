package org.planth_pheno_analytics.data.file;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTrial {
    @JsonProperty("Investigation Identifier")
    private String investigationIdentifier;
    @JsonProperty("Investigation Title")
    private String investigationTitle;
    @JsonProperty("Study Title")
    private String StudyTitle;
    @JsonProperty("Crop")
    private String crop;
    @JsonProperty("FinalData")
    private Map<String, List<Map<String, String>>> finalData;
    @JsonProperty("Variables")
    private Map<String, String> variables;

    public FileTrial() {
        this.finalData = new HashMap<>();
        this.variables = new HashMap<>();
    }

    public String getInvestigationIdentifier() {
        return investigationIdentifier;
    }

    public void setInvestigationIdentifier(String investigationIdentifier) {
        this.investigationIdentifier = investigationIdentifier;
    }

    public String getInvestigationTitle() {
        return investigationTitle;
    }

    public void setInvestigationTitle(String investigationTitle) {
        this.investigationTitle = investigationTitle;
    }

    public String getStudyTitle() {
        return StudyTitle;
    }

    public void setStudyTitle(String studyTitle) {
        StudyTitle = studyTitle;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public Map<String, List<Map<String, String>>> getFinalData() {
        return finalData;
    }

    public void setFinalData(Map<String, List<Map<String, String>>> finalData) {
        this.finalData = finalData;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
}
