package org.brapi_igpas.brapi.domain.calls;

import java.util.List;

public abstract class ListWrapper<T> {
    protected List<T> list;

    public ListWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }
}
