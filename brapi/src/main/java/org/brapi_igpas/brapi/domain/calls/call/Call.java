package org.brapi_igpas.brapi.domain.calls.call;

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

    public Call(String call) {
        this();
        this.call = call;
    }

    public Call withDataTypeJson() {
        return getThisWithAddedDataType(DATATYPE_JSON);
    }

    public Call withDataTypeCsv() {
        return getThisWithAddedDataType(DATATYPE_CSV);
    }

    private Call getThisWithAddedDataType(String dataType) {
        dataTypes.add(dataType);
        return this;
    }

    public Call withMethodGet() {
        return getThisWithAddedMethod(METHOD_GET);
    }

    public Call withMethodPost() {
        return getThisWithAddedMethod(METHOD_POST);
    }

    public Call withMethodPut() {
        return getThisWithAddedMethod(METHOD_PUT);
    }

    public Call withMethodDelete() {
        return getThisWithAddedMethod(METHOD_DELETE);
    }

    private Call getThisWithAddedMethod(String method) {
        methods.add(method);
        return this;
    }

    public Call withVersionOneZero() {
        return getThisWithAddedVersion(VERSION_ONE_ZERO);
    }

    public Call withVersionOneOne() {
        return getThisWithAddedVersion(VERSION_ONE_ONE);
    }

    public Call withVersionOneTwo() {
        return getThisWithAddedVersion(VERSION_ONE_TWO);
    }

    public Call withVersionOneThree() {
        return getThisWithAddedVersion(VERSION_ONE_THREE);
    }

    private Call getThisWithAddedVersion(String version) {
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
