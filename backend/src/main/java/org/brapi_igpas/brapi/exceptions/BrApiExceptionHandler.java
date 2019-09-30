package org.brapi_igpas.brapi.exceptions;

import org.brapi_igpas.brapi.BrApiDetailResponse;
import org.brapi_igpas.brapi.metadata.Metadata;
import org.brapi_igpas.brapi.metadata.Pagination;
import org.brapi_igpas.brapi.metadata.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;

@ControllerAdvice
public class BrApiExceptionHandler {
    @ExceptionHandler(InvalidNumericalParameterValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    BrApiDetailResponse invalidNumericalParameterException(InvalidNumericalParameterValueException exc) {
        String parameter = exc.getParameter();
        BrApiDetailResponse brApiDetailResponse = new BrApiDetailResponse(new ArrayList<>(), new Pagination());
        Metadata metadata = brApiDetailResponse.getMetadata();
        metadata.setStatus(Collections.singletonList(new Status("'" + parameter + "' value is invalid", Status.MESSAGETYPE_LOG_ERROR)));
        return brApiDetailResponse;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    BrApiDetailResponse invalidNumberFormatParameterException() {
        BrApiDetailResponse brApiDetailResponse = new BrApiDetailResponse(new ArrayList<>(), new Pagination());
        Metadata metadata = brApiDetailResponse.getMetadata();
        metadata.setStatus(Collections.singletonList(new Status("Invalid query parameter", Status.MESSAGETYPE_LOG_ERROR)));
        return brApiDetailResponse;
    }
}
