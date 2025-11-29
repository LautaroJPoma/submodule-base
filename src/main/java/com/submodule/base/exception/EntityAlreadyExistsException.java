package com.submodule.base.exception;

/**
 * Se lanza cuando se intenta crear una entidad que ya existe.
 * HTTP Status: 409 CONFLICT
 */
public class EntityAlreadyExistsException extends BaseException {
    
    public EntityAlreadyExistsException(String entityName, String field, Object value) {
        super(String.format("%s already exists with %s: %s", entityName, field, value));
    }
}