package org.vahad.opdracht.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TODO (Daniël Bosmans): Add file description here
 *
 * @author Daniël Bosmans
 */
@ResponseStatus(HttpStatus.CONFLICT)
public final class ExceptionHandeling extends RuntimeException {
    
    public ExceptionHandeling(String userId) {
        super("could not find user '" + userId + "'.");
    }
}
