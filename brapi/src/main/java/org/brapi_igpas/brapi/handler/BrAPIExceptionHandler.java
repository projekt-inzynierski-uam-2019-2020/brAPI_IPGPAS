package org.brapi_igpas.brapi.handler;

import org.brapi_igpas.brapi.response.BrAPIDetailResponse;
import org.brapi_igpas.brapi.response.metadata.Metadata;
import org.brapi_igpas.brapi.response.metadata.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Set;

@ControllerAdvice
public class BrAPIExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    BrAPIDetailResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder messages = getConstraintViolationsMessages(e.getConstraintViolations());
        return getBrApiDetailResponseWithErrorMessage(messages.toString());
    }

    private StringBuilder getConstraintViolationsMessages(Set<ConstraintViolation<?>> constraintViolations) {
        StringBuilder messages = new StringBuilder();
        for (ConstraintViolation<?> violation : constraintViolations) {
            messages.append(violation.getMessage());
        }
        return messages;
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    BrAPIDetailResponse handleNumberFormatException() {
        return getBrApiDetailResponseWithErrorMessage("Invalid parameter query");
    }

    private BrAPIDetailResponse getBrApiDetailResponseWithErrorMessage(String message) {
        BrAPIDetailResponse response = new BrAPIDetailResponse();
        Metadata metadata = response.getMetadata();
        Status status = new Status(message, Status.MESSAGETYPE_LOG_ERROR);
        metadata.setStatus(Collections.singletonList(status));
        return response;
    }
}
