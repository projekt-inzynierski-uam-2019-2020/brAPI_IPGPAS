package org.planth_pheno_analytics.data.file;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFile {
    @JsonProperty("trials")
    private List<FileTrial> fileTrials = new ArrayList<>();

    public DatabaseFile() {
    }

    public List<FileTrial> getFileTrials() {
        return fileTrials;
    }

    public void setFileTrials(List<FileTrial> fileTrials) {
        this.fileTrials = fileTrials;
    }
}
