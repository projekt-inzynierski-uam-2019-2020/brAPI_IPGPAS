package org.brapi_igpas.brapi.calls.calls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Call {

    private String call;
    private List<String> dataTypes;
    private List<String> methods;
    private List<String> versions;

    private Call() {}

    static class Builder {
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

        Builder(String call){
            this.call = call;
            this.dataTypes = new ArrayList<>();
            this.methods = new ArrayList<>();
            this.versions = new ArrayList<>();
        }

        Builder withDataTypeJson() {
            return withAddedDataType(DATATYPE_JSON);
        }

        Builder withDataTypeCsv() {
            return withAddedDataType(DATATYPE_CSV);
        }

        private Builder withAddedDataType(String dataType) {
            dataTypes.add(dataType);
            return this;
        }

        Builder withMethodGet() {
            return withAddedMethod(METHOD_GET);
        }

        Builder withMethodPost() {
            return withAddedMethod(METHOD_POST);
        }

        Builder withMethodPut() {
            return withAddedMethod(METHOD_PUT);
        }

        Builder withMethodDelete() {
            return withAddedMethod(METHOD_DELETE);
        }

        private Builder withAddedMethod(String method) {
            methods.add(method);
            return this;
        }

        Builder withVersionOneZero() {
            return withAddedVersion(VERSION_ONE_ZERO);
        }

        Builder withVersionOneOne() {
            return withAddedVersion(VERSION_ONE_ONE);
        }

        Builder withVersionOneTwo() {
            return withAddedVersion(VERSION_ONE_TWO);
        }

        Builder withVersionOneThree() {
            return withAddedVersion(VERSION_ONE_THREE);
        }

        private Builder withAddedVersion(String version) {
            versions.add(version);
            return this;
        }

        Call build(){
            Call call = new Call();
            call.call = this.call;
            call.dataTypes = this.dataTypes;
            call.methods = this.methods;
            call.versions = this.versions;
            return call;
        }
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
