package com.submodule.base.exception;

/**
 * Se lanza cuando se viola una regla de negocio.
 * HTTP Status: 422 UNPROCESSABLE ENTITY
 */
public class BusinessRuleException extends BaseException {
    
    public BusinessRuleException(String message) {
        super(message);
    }
}