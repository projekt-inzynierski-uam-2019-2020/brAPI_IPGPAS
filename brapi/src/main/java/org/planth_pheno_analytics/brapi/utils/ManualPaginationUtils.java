package org.planth_pheno_analytics.brapi.utils;

import java.util.Collections;
import java.util.List;

class ManualPaginationUtils {

    static int getTotalPages(int elementsSize, int pageSize) {
        final int remainderPage = 1;
        return elementsSize / pageSize + ((elementsSize % pageSize == 0) ? 0 : remainderPage);
    }

    static <T> List<T> paginateList(List<T> list, int page, int pageSize) {
        int fromIndex = page * pageSize;
        int elementsSize = list.size();

        if (fromIndex < elementsSize) {
            int toIndex = getToIndex(fromIndex, elementsSize, pageSize);
            return list.subList(fromIndex, toIndex);
        }
        return Collections.emptyList();
    }

    private static int getToIndex(int fromIndex, int elementsSize, int pageSize) {
        int numberOfRemainingElements = elementsSize - fromIndex;
        int complement = Math.min(pageSize, numberOfRemainingElements);
        return fromIndex + complement;
    }
}
