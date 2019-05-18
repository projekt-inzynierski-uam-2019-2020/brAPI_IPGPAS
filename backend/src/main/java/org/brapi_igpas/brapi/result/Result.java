package org.brapi_igpas.brapi.result;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<?> data;

    public Result() {
        data = new ArrayList<>();
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
