package org.brapi_igpas.brapi.calls;

import java.util.List;

public abstract class ListWrapper<T> {
    protected List<T> list;

    public ListWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public int size() {
        return list.size();
    }
}
