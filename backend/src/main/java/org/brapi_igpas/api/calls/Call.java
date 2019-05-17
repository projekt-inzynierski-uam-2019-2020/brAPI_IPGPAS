package org.brapi_igpas.api.calls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Call {
    public static final String DATATYPE_JSON = "application/json";
    public static final String DATATYPE_CSV = "text/csv";

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";

    public static final String VERSION_ONE_ZERO = "1.0";
    public static final String VERSION_ONE_ONE = "1.1";
    public static final String VERSION_ONE_TWO = "1.2";
    public static final String VERSION_ONE_THREE = "1.3";

    private String call;
    private List<String> dataTypes;
    private List<String> methods;
    private List<String> versions;

    public Call(){
        dataTypes = new ArrayList<>();
        methods = new ArrayList<>();
        versions = new ArrayList<>();
    }

    public Call(String call) {
        this();
        this.call = call;
    }

    public Call withDataTypeJson() {
        return addDataType(DATATYPE_JSON);
    }

    public Call withDataTypeCsv() {
        return addDataType(DATATYPE_CSV);
    }

    private Call addDataType(String dataType) {
        dataTypes.add(dataType);
        return this;
    }

    public Call withMethodGet() {
        return addMethod(METHOD_GET);
    }

    public Call withMethodPost() {
        return addMethod(METHOD_POST);
    }

    public Call withMethodPut() {
        return addMethod(METHOD_PUT);
    }

    public Call withMethodDelete() {
        return addMethod(METHOD_DELETE);
    }

    private Call addMethod(String method) {
        methods.add(method);
        return this;
    }

    public Call withVersionOneZero() {
        return addVersion(VERSION_ONE_ZERO);
    }

    public Call withVersionOneOne() {
        return addVersion(VERSION_ONE_ONE);
    }

    public Call withVersionOneTwo() {
        return addVersion(VERSION_ONE_TWO);
    }

    public Call withVersionOneThree() { return addVersion(VERSION_ONE_THREE); }

    private Call addVersion(String version) {
        versions.add(version);
        return this;
    }

    public String getCall() {
        return call;
    }

    public List<String> getDataTypes() {
        return dataTypes;
    }

    public List<String> getMethods() {
        return methods;
    }

    public List<String> getVersions() {
        return versions;
    }

    @Override
    public String toString() {
        return "Call{" +
                "call='" + call + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call1 = (Call) o;
        return Objects.equals(call, call1.call) &&
                Objects.equals(dataTypes, call1.dataTypes) &&
                Objects.equals(methods, call1.methods) &&
                Objects.equals(versions, call1.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(call, dataTypes, methods, versions);
    }
}
