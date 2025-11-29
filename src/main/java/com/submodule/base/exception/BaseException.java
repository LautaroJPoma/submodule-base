package com.submodule.base.exception;

/**
 * Excepción base para todas las excepciones del submódulo.
 * Permite capturar cualquier error del sistema de forma genérica.
 */
public abstract class BaseException extends RuntimeException {
    
    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}