package org.brapi_igpas.brapi.domain.response.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Result {
    private List<?> data;

    public Result() {
        data = new ArrayList<>();
    }

    public Result(List<?> data) {
        this.data = data;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(data, result.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
