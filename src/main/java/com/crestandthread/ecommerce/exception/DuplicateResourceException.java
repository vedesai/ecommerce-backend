// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
// AI Generated Code by Deloitte + Cursor (END)