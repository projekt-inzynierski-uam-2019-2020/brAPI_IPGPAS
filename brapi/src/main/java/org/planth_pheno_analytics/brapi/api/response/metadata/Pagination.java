package org.planth_pheno_analytics.brapi.api.response.metadata;

import org.springframework.data.domain.Page;

import java.util.Objects;

public class Pagination {
    private int currentPage;
    private int pageSize;
    private long totalCount;
    private int totalPages;

    private Pagination(int currentPage, int pageSize, long totalCount, int totalPages) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }

    public static Pagination of(int currentPage, int pageSize, long totalCount, int totalPages) {
        return new Pagination(currentPage, pageSize, totalCount, totalPages);
    }

    public static Pagination of(Page page) {
        return new Pagination(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }

    public static Pagination empty() {
        return new Pagination(0, 0, 0, 0);
    }

    public long getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagination that = (Pagination) o;
        return currentPage == that.currentPage &&
                pageSize == that.pageSize &&
                totalCount == that.totalCount &&
                totalPages == that.totalPages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPage, pageSize, totalCount, totalPages);
    }
}
