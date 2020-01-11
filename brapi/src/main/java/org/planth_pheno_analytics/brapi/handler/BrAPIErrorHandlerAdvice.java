package org.planth_pheno_analytics.brapi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class BrAPIErrorHandlerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BrAPIError handleBindException(BindException ex) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errors.append(fieldError.getDefaultMessage());
        }
        return new BrAPIError(errors.toString());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BrAPIError handleNumberFormatException() {
        return new BrAPIError("Malformed JSON Request Object");
    }
}
