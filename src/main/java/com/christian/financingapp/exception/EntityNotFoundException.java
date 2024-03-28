package com.christian.financingapp.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> entity) {
        super(entity.getSimpleName() + " not found");
    }
}