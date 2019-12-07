package org.planth_pheno_analytics.brapi.api.criteria;

import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

public class PaginationCriteria {
    @PositiveOrZero(message = "'page' value is invalid.")
    private int page = 0;

    @Min(value = 1, message = "'pageSize' value is invalid.")
    private int pageSize = 1000;

    public PaginationCriteria() {
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
