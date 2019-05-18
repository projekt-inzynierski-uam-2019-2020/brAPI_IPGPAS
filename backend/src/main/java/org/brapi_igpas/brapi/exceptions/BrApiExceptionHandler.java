package org.brapi_igpas.brapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BrApiExceptionHandler {

    @ExceptionHandler(InvalidPageValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String invalidPageValue(){
        return "\"'page' value is invalid\"";
    }

    @ExceptionHandler(InvalidPageSizeValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String invalidPageSizeValue(){ return "\"'pageSize' value is invalid\""; }
}
