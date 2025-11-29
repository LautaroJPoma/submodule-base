package com.submodule.base.exception;

/**
 * Se lanza cuando no se encuentra una entidad por su ID.
 * HTTP Status: 404 NOT FOUND
 */
public class EntityNotFoundException extends BaseException {
    
    public EntityNotFoundException(String entityName, Object id) {
        super(String.format("%s not found with id: %s", entityName, id));
    }
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}