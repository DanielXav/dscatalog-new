package com.danielxavier.dsnewcatalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message){
        super(message);
    }
}
