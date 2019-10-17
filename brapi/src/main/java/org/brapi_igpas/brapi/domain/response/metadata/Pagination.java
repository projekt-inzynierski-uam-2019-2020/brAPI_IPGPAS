package org.brapi_igpas.brapi.domain.response.metadata;

import java.util.Objects;

public class Pagination {
    private int currentPage;
    private int pageSize;
    private long totalCount;
    private int totalPages;

    public Pagination() {
    }

    public Pagination(int currentPage, int pageSize, long totalCount, int totalPages) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
