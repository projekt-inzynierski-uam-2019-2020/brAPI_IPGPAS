package org.brapi_igpas.brapi;

import org.brapi_igpas.brapi.metadata.Pagination;

public class PaginationUtils {

    public static Pagination getPaginationInfo(int elementsSize, int page, int pageSize){
        int totalPages = getTotalPages(elementsSize, pageSize);
        return new Pagination(page, pageSize, elementsSize, totalPages);
    }

    public static int getNumberOfElementsOnCurrentPage(int elementsSize, int page, int pageSize){
        if(page * pageSize >= elementsSize){
            return 0;
        }
        int totalPages = getTotalPages(elementsSize, pageSize);

        int fromIndex = 0;
        if (page < totalPages) {
            fromIndex = getFromIndex(elementsSize, page, pageSize);
        }
        return Math.min(pageSize, elementsSize - fromIndex);
    }

    private static int getTotalPages(int elementsSize, int pageSize){
        final int zeroPage = 1;
        int totalPages = (int) Math.ceil(elementsSize / pageSize);
        return  totalPages == 0 ? totalPages + zeroPage : totalPages;
    }

    public static int getFromIndex(int elementsSize, int page, int pageSize){
        if(page * pageSize >= elementsSize){
            return 0;
        }
        else return page * pageSize;
    }

}
