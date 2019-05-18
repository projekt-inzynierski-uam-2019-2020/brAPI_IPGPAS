package org.brapi_igpas.brapi.metadata;

import java.util.ArrayList;
import java.util.List;

public class Metadata {
    private List<String> datafiles;
    private Pagination pagination;
    private List<Status> status;

    public Metadata() {
        datafiles = new ArrayList<>();
        pagination = new Pagination();
        status = new ArrayList<>();
    }

    public List<String> getDatafiles() { return datafiles; }

    public void setDatafiles(List<String> datafiles) {
        this.datafiles = datafiles;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
