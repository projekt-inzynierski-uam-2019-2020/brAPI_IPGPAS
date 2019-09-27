package org.brapi_igpas.brapi.calls.calls;

import java.util.ArrayList;
import java.util.List;

public class Call {
    static final String DATATYPE_JSON = "application/json";
    static final String DATATYPE_CSV = "text/csv";

    static final String METHOD_GET = "GET";
    static final String METHOD_POST = "POST";
    static final String METHOD_PUT = "PUT";
    static final String METHOD_DELETE = "DELETE";

    static final String VERSION_ONE_ZERO = "1.0";
    static final String VERSION_ONE_ONE = "1.1";
    static final String VERSION_ONE_TWO = "1.2";
    static final String VERSION_ONE_THREE = "1.3";

    private String call;
    private List<String> dataTypes;
    private List<String> methods;
    private List<String> versions;

    Call() {
        dataTypes = new ArrayList<>();
        methods = new ArrayList<>();
        versions = new ArrayList<>();
    }

    Call(String call) {
        this();
        this.call = call;
    }

    Call withDataTypeJson() {
        return addDataType(DATATYPE_JSON);
    }

    Call withDataTypeCsv() {
        return addDataType(DATATYPE_CSV);
    }

    private Call addDataType(String dataType) {
        dataTypes.add(dataType);
        return this;
    }

    Call withMethodGet() {
        return addMethod(METHOD_GET);
    }

    Call withMethodPost() {
        return addMethod(METHOD_POST);
    }

    Call withMethodPut() {
        return addMethod(METHOD_PUT);
    }

    Call withMethodDelete() {
        return addMethod(METHOD_DELETE);
    }

    private Call addMethod(String method) {
        methods.add(method);
        return this;
    }

    Call withVersionOneZero() {
        return addVersion(VERSION_ONE_ZERO);
    }

    Call withVersionOneOne() {
        return addVersion(VERSION_ONE_ONE);
    }

    Call withVersionOneTwo() {
        return addVersion(VERSION_ONE_TWO);
    }

    Call withVersionOneThree() {
        return addVersion(VERSION_ONE_THREE);
    }

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
}
