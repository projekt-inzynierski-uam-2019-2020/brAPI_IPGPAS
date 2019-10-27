package org.brapi_igpas.brapi.calls.calls;

import java.util.ArrayList;
import java.util.List;

class Call {
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

    Call(String call) {
        this.call = call;
        dataTypes = new ArrayList<>();
        methods = new ArrayList<>();
        versions = new ArrayList<>();
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

    Call withDataTypeJson() {
        return getThisWithAddedDataType(DATATYPE_JSON);
    }

    Call withDataTypeCsv() {
        return getThisWithAddedDataType(DATATYPE_CSV);
    }

    private Call getThisWithAddedDataType(String dataType) {
        dataTypes.add(dataType);
        return this;
    }

    Call withMethodGet() {
        return getThisWithAddedMethod(METHOD_GET);
    }

    Call withMethodPost() {
        return getThisWithAddedMethod(METHOD_POST);
    }

    Call withMethodPut() {
        return getThisWithAddedMethod(METHOD_PUT);
    }

    Call withMethodDelete() {
        return getThisWithAddedMethod(METHOD_DELETE);
    }

    private Call getThisWithAddedMethod(String method) {
        methods.add(method);
        return this;
    }

    Call withVersionOneZero() {
        return getThisWithAddedVersion(VERSION_ONE_ZERO);
    }

    Call withVersionOneOne() {
        return getThisWithAddedVersion(VERSION_ONE_ONE);
    }

    Call withVersionOneTwo() {
        return getThisWithAddedVersion(VERSION_ONE_TWO);
    }

    Call withVersionOneThree() {
        return getThisWithAddedVersion(VERSION_ONE_THREE);
    }

    private Call getThisWithAddedVersion(String version) {
        versions.add(version);
        return this;
    }
}
