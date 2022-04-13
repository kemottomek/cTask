package com.cisco.app.constroller;
/*
 * @author nbtwszol
 */

import com.cisco.app.exeption.ControllerException;
import com.cisco.app.generated.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ControllerException.class})
    protected ResponseEntity<Object> handleControllerException(ControllerException ex, WebRequest request) {
        var result = new Error();
        result.setCode(Integer.toString(ex.getStatus().value()));
        result.setReason(ex.getReason());
        result.setStatus(ex.getStatus().toString());
        return handleExceptionInternal(ex, result,
                new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleOthersException(Exception e, WebRequest request) {
        var result = new Error();
        result.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        result.setReason(e.getMessage());
        result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return handleExceptionInternal(e, result,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}