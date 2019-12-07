package org.planth_pheno_analytics.brapi.api.response.metadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Metadata {
    private List<String> datafiles;
    private Pagination pagination;
    private List<Status> status;

    private Metadata(){
    }

    public static class Builder {
        private List<String> datafiles;
        private Pagination pagination;
        private List<Status> status;

        public Builder() {
            datafiles = new ArrayList<>();
            status = new ArrayList<>();
        }

        public Builder withDataFiles(List<String> dataFiles) {
            this.datafiles = dataFiles;
            return this;
        }

        public Builder withPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public Builder withStatus(List<Status> status) {
            this.status = status;
            return this;
        }

        public Metadata build() {
            Metadata metadata = new Metadata();
            metadata.datafiles = this.datafiles;
            metadata.pagination = this.pagination;
            metadata.status = this.status;
            return metadata;
        }
    }

    public List<String> getDatafiles() {
        return datafiles;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public List<Status> getStatus() {
        return status;
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
