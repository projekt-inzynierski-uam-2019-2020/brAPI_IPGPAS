package org.brapi_igpas.brapi.exceptions;

import org.brapi_igpas.brapi.BrApiDetailPayloadResponse;
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

    public static final String LOG_INFO = "INFO";

    @ExceptionHandler(InvalidPageValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    BrApiDetailPayloadResponse invalidPageValue(){
        BrApiDetailPayloadResponse brApiDetailPayloadResponse = new BrApiDetailPayloadResponse(new ArrayList<>(), new Pagination());
        brApiDetailPayloadResponse.getMetadata().setStatus(Collections.singletonList(new Status("'page' value is invalid", LOG_INFO)));

        return brApiDetailPayloadResponse;
    }

    @ExceptionHandler(InvalidPageSizeValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody BrApiDetailPayloadResponse invalidPageSizeValue(){
        BrApiDetailPayloadResponse brApiDetailPayloadResponse = new BrApiDetailPayloadResponse(new ArrayList<>(), new Pagination());
        brApiDetailPayloadResponse.getMetadata().setStatus(Collections.singletonList(new Status("'pageSize' value is invalid", LOG_INFO)));

        return brApiDetailPayloadResponse;
    }
}
