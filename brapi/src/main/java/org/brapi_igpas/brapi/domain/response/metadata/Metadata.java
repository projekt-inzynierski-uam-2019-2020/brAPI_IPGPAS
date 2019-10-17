package org.brapi_igpas.brapi.domain.response.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Metadata {
    private List<String> datafiles;
    private Pagination pagination;
    private List<Status> status;

    public Metadata() {
        datafiles = new ArrayList<>();
        pagination = new Pagination();
        status = new ArrayList<>();
    }

    public List<String> getDatafiles() {
        return datafiles;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(datafiles, metadata.datafiles) &&
                Objects.equals(pagination, metadata.pagination) &&
                Objects.equals(status, metadata.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datafiles, pagination, status);
    }
}
