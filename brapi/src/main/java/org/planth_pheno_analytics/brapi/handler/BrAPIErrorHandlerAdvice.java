package org.planth_pheno_analytics.brapi.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BrAPIErrorHandlerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BrAPIError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new BrAPIError(ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BrAPIError handleBindException(BindException ex) {
        return new BrAPIError(ex.getBindingResult().getFieldErrors().get(0).toString());
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BrAPIError handleNumberFormatException(NumberFormatException ex) {
        return new BrAPIError(ex.getMessage());
    }
}
