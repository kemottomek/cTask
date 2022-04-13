package com.cisco.app.exeption;
/*
 * @author nbtwszol
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ControllerException extends ResponseStatusException {
    public ControllerException(Long id, HttpStatus status) {
        super(status, String.format("Cannot find %s", id));
    }

    public ControllerException(String id, HttpStatus status) {
        super(status, String.format("Cannot find %s", id));
    }
}
