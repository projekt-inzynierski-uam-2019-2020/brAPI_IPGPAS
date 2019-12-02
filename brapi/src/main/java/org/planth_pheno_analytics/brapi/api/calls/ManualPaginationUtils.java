package org.planth_pheno_analytics.brapi.api.calls;

import org.planth_pheno_analytics.brapi.api.response.metadata.Pagination;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ManualPaginationUtils {

    Pagination getPagination(int totalCount, int page, int pageSize) {
        int totalPages = getTotalPages(totalCount, pageSize);
        return Pagination.of(page, pageSize, totalCount, totalPages);
    }

    int getTotalPages(int elementsSize, int pageSize) {
        final int remainderPage = 1;
        if (elementsSize == 0) {
            return remainderPage;
        }
        return elementsSize / pageSize + ((elementsSize % pageSize == 0) ? 0 : remainderPage);
    }

    <T> List<T> paginateList(List<T> list, int page, int pageSize) {
        int fromIndex = page * pageSize;
        int elementsSize = list.size();

        if (fromIndex < elementsSize) {
            int toIndex = getToIndex(fromIndex, elementsSize, pageSize);
            return list.subList(fromIndex, toIndex);
        }
        return Collections.emptyList();
    }

    private int getToIndex(int fromIndex, int elementsSize, int pageSize) {
        int numberOfRemainingElements = elementsSize - fromIndex;
        int complement = Math.min(pageSize, numberOfRemainingElements);
        return fromIndex + complement;
    }
}
