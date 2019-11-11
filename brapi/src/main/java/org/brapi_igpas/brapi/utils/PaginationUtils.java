package org.brapi_igpas.brapi.utils;

import org.brapi_igpas.brapi.response.metadata.Pagination;

import java.util.Collections;
import java.util.List;

public class PaginationUtils {

    public static Pagination getPaginationInfo(int totalCount, int page, int pageSize) {
        int totalPages = getTotalPages(totalCount, pageSize);
        return new Pagination(page, pageSize, totalCount, totalPages);
    }

    static int getTotalPages(int elementsSize, int pageSize) {
        final int remainderPage = 1;
        if (elementsSize == 0) {
            return remainderPage;
        }
        return elementsSize / pageSize + ((elementsSize % pageSize == 0) ? 0 : remainderPage);
    }

    public static <T> List<T> getPaginatedList(List<T> list, int page, int pageSize) {
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
