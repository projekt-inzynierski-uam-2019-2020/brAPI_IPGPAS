package org.planth_pheno_analytics.brapi.api;

import java.util.Collections;
import java.util.List;

public abstract class Sorter<T> {

    protected abstract List<T> sortBy(List<T> stream, String sortBy);

    public List<T> sortOrder(List<T> list, String sortOrder) {
        if (sortOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(list);
        }
        return list;
    }
}
