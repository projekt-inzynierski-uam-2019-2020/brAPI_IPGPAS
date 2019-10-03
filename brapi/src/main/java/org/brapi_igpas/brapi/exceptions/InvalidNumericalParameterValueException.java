package org.brapi_igpas.brapi.exceptions;

public class InvalidNumericalParameterValueException extends RuntimeException{
    private String parameter;

    public InvalidNumericalParameterValueException(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
