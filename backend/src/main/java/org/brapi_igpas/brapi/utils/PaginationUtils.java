package org.brapi_igpas.brapi.utils;

import org.brapi_igpas.brapi.metadata.Pagination;

public class PaginationUtils {

    public static Pagination getPaginationInfo(int totalCount, int page, int pageSize) {
        int totalPages = getTotalPages(totalCount, pageSize);
        return new Pagination(page, pageSize, totalCount, totalPages);
    }

    public static int getFromIndex(int elementsSize, int page, int pageSize) {
        final int firstIndex = 0;
        if (isFromIndexWithinPaginationBorders(elementsSize, page, pageSize)) {
            return page * pageSize;
        } else return firstIndex;
    }

    public static int getToIndex(int elementsSize, int page, int pageSize) {
        if (!isFromIndexWithinPaginationBorders(elementsSize, page, pageSize)) {
            return 0;
        }
        int fromIndex = getFromIndex(elementsSize, page, pageSize);
        int numberOfElements = Math.min(pageSize, elementsSize - fromIndex);
        return fromIndex + numberOfElements;
    }

    private static boolean isFromIndexWithinPaginationBorders(int elementsSize, int page, int pageSize) {
        return page * pageSize < elementsSize;
    }

    private static int getTotalPages(int elementsSize, int pageSize) {
        final int remainderPage = 1;
        if (elementsSize == 0) {
            return remainderPage;
        }
        return elementsSize / pageSize + ((elementsSize % pageSize == 0) ? 0 : remainderPage);
    }
}
