package org.planth_pheno_analytics.brapi.api.criteria;

import javax.validation.constraints.Size;

public class SortCriteria {
    private String sortBy;

    @Size(max = 3, message = "sortOrder value is too long")
    private String sortOrder = "ASC";

    public SortCriteria() {
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
