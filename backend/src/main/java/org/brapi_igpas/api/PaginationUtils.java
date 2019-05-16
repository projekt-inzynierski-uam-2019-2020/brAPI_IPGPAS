package org.brapi_igpas.api;

public class PaginationUtils {

    public static int getFromIndex(int elementsSize, int page, int pageSize){
        if(page * pageSize >= elementsSize){
            return 0;
        }
        else return page * pageSize;
    }

    public static int getNumberOfElementsOnCurrentPage(int elementsSize, int page, int pageSize){
        if(page * pageSize >= elementsSize){
            return 0;
        }
        int totalPages = (int) Math.ceil(elementsSize / pageSize);

        int fromIndex = 0;
        if (page < totalPages) {
            fromIndex = getFromIndex(elementsSize, page, pageSize);
        }
        return Math.min(pageSize, elementsSize - fromIndex);
    }
}
