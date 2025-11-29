package com.submodule.base.exception;

/**
 * Se lanza cuando los datos de entrada son inválidos.
 * HTTP Status: 400 BAD REQUEST
 */
public class InvalidRequestException extends BaseException {
    
    public InvalidRequestException(String message) {
        super(message);
    }
    
    public InvalidRequestException(String field, String reason) {
        super(String.format("Invalid field '%s': %s", field, reason));
    }
}