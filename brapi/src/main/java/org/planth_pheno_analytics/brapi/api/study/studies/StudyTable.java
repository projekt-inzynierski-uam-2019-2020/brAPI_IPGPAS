package org.planth_pheno_analytics.brapi.api.study.studies;

import java.util.ArrayList;
import java.util.List;

public class StudyTable {
    private List<List<String>> data;
    private List<String> headers;
    private List<String> observationVariableDbIds;
    private List<String> observationVariableNames;

    public StudyTable() {
        this.data = new ArrayList<>();
        this.headers = new ArrayList<>();
        this.observationVariableDbIds = new ArrayList<>();
        this.observationVariableNames = new ArrayList<>();
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getObservationVariableDbIds() {
        return observationVariableDbIds;
    }

    public void setObservationVariableDbIds(List<String> observationVariableDbIds) {
        this.observationVariableDbIds = observationVariableDbIds;
    }

    public List<String> getObservationVariableNames() {
        return observationVariableNames;
    }

    public void setObservationVariableNames(List<String> observationVariableNames) {
        this.observationVariableNames = observationVariableNames;
    }
}
