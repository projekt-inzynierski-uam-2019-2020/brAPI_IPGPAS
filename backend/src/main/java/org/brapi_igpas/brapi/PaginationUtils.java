package org.brapi_igpas.brapi;

import org.brapi_igpas.brapi.metadata.Pagination;

public class PaginationUtils {

    public static Pagination getPaginationInfo(int totalCount, int page, int pageSize) {
        int totalPages = getTotalPages(totalCount, pageSize);
        return new Pagination(page, pageSize, totalCount, totalPages);
    }

    public static int getFromIndex(int elementsSize, int page, int pageSize) {
        final int firstIndex = 0;
        if (isFromIndexInPaginationBorders(elementsSize, page, pageSize)) {
            return page * pageSize;
        } else return firstIndex;
    }

    public static int getToIndex(int elementsSize, int page, int pageSize) {
        if (!isFromIndexInPaginationBorders(elementsSize, page, pageSize)) {
            return 0;
        }
        int fromIndex = getFromIndex(elementsSize, page, pageSize);
        int numberOfElements = Math.min(pageSize, elementsSize - fromIndex);

        return fromIndex + numberOfElements;
    }

    private static boolean isFromIndexInPaginationBorders(int elementsSize, int page, int pageSize) {
        return page * pageSize < elementsSize;
    }

    private static int getTotalPages(int elementsSize, int pageSize) {
        final int zeroPage = 1;
        int totalPages = (int) Math.ceil(elementsSize / pageSize);
        return totalPages == 0 ? totalPages + zeroPage : totalPages;
    }
}
